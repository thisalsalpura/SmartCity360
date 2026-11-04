package com.thisal.smartcity360.service;

import com.thisal.smartcity360.pattern.composite.city_zone_composite.CityZone;
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

    private CityZone findParent(CityZone current, String childId) {
        for (CityZone child : current.getChildren()) {
            if (child.getId().equals(childId)) {
            }
        }
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