package com.thisal.smartcity360.service;

import com.thisal.smartcity360.enums.ZoneLevel;
import com.thisal.smartcity360.exception.InvalidZoneOperationException;
import com.thisal.smartcity360.exception.ZoneNotFoundException;
import com.thisal.smartcity360.pattern.composite.city_zone_composite.CityZone;
import com.thisal.smartcity360.pattern.composite.city_zone_composite.CityZoneFactory;
import com.thisal.smartcity360.pattern.composite.city_zone_composite.ZoneComposite;
import com.thisal.smartcity360.record.PowerActionReport;
import com.thisal.smartcity360.record.ZoneView;
import com.thisal.smartcity360.repo.CityZoneRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

@ApplicationScoped
public class CityZoneService {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final CityZoneRepo repo;

    protected CityZoneService() {
        this.repo = null;
    }

    @Inject
    public CityZoneService(CityZoneRepo repo) {
        this.repo = Objects.requireNonNull(repo, "CityZone Repo must not be Null!");
    }

    private static ZoneView toView(CityZone zone) {
        return new ZoneView(
                zone.getId(),
                zone.getName(),
                zone.getLevel(),
                zone.reportUsage(),
                zone.isPoweredOn(),
                zone.getChildren().stream().map(CityZoneService::toView).toList()
        );
    }

    public ZoneView tree() {
        return read(() -> {
            assert repo != null;
            return toView(repo.root());
        });
    }

    public double usage(String zoneId) {
        return read(() -> require(zoneId).reportUsage());
    }

    public PowerActionReport shutdownPower(String zoneId) {
        return write(() -> {
            CityZone zone = require(zoneId);
            int affected = zone.shutdownPower();
            return powerReport(zone, "SHUTDOWN", affected);
        });
    }

    public PowerActionReport restorePower(String zoneId) {
        return write(() -> {
            CityZone zone = require(zoneId);
            int affected = zone.restorePower();
            return powerReport(zone, "RESTORE", affected);
        });
    }

    public ZoneView addZone(String parentId, ZoneLevel level, String name, double kilowattHours) {
        return write(() -> {
            CityZone parent = require(parentId);
            if (!(parent instanceof ZoneComposite composite)) {
                throw new InvalidZoneOperationException("A Building cannot contain other Zones!");
            }
            assert repo != null;
            CityZone zone = CityZoneFactory.create(level, repo.nextId(level), name, kilowattHours);
            composite.add(zone);
            repo.register(zone);
            return toView(zone);
        });
    }

    public void removeZone(String zoneId) {
        write(() -> {
            CityZone zone = require(zoneId);
            assert repo != null;
            if (repo.isRoot(zoneId)) {
                throw new InvalidZoneOperationException("The City Root cannot be Removed!");
            }
            CityZone parent = findParent(repo.root(), zoneId);
            if (parent instanceof ZoneComposite composite) {
                composite.remove(zoneId);
                repo.unregister(zone);
            }
            return null;
        });
    }

    private PowerActionReport powerReport(CityZone zone, String action, int affected) {
        return new PowerActionReport(zone.getId(), zone.getName(), zone.getLevel(), action, affected, zone.reportUsage());
    }

    private CityZone require(String zoneId) {
        assert repo != null;
        return repo.find(zoneId).orElseThrow(() -> new ZoneNotFoundException("ZoneId=" + zoneId + " is Not Found!"));
    }

    private CityZone findParent(CityZone current, String childId) {
        for (CityZone child : current.getChildren()) {
            if (child.getId().equals(childId)) {
                return current;
            }
            CityZone found = findParent(child, childId);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    private <T> T read(Supplier<T> action) {
        lock.readLock().lock();
        try {
            return action.get();
        } finally {
            lock.readLock().unlock();
        }
    }

    private <T> T write(Supplier<T> action) {
        lock.writeLock().lock();
        try {
            return action.get();
        } finally {
            lock.writeLock().unlock();
        }
    }

}