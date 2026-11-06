package com.thisal.smartcity360.service;

import com.thisal.smartcity360.enums.LampTechnology;
import com.thisal.smartcity360.pattern.flyweight.street_light_flyweight.StreetLightTypeFactory;
import com.thisal.smartcity360.record.StreetLightSpec;
import com.thisal.smartcity360.repo.StreetLightRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

@ApplicationScoped
public class StreetLightService {

    static final int MAX_FLEET = 100_000;

    private static final int DEFAULT_SAMPLE = 20;

    private static final int MAX_SAMPLE = 100;

    private static final int SEED_LIGHTS = 12;

    private static final long ESTIMATED_TYPE_BYTES = 160;

    private static final long ESTIMATED_LIGHT_BYTES = 80;

    private static final List<StreetLightSpec> CATALOG = List.of(
            new StreetLightSpec("LED-60", LampTechnology.LED, 60, 7800, LocalTime.of(18, 0), LocalTime.of(6, 0)),
            new StreetLightSpec("LED-90", LampTechnology.LED, 90, 11700, LocalTime.of(18, 0), LocalTime.of(6, 0)),
            new StreetLightSpec("SON-150", LampTechnology.SODIUM, 150, 15000, LocalTime.of(18, 30), LocalTime.of(5, 30))
    );

    private static final List<String> STREETS = List.of(
            "York Street",
            "Chatham Street",
            "Main Street",
            "Galle Road",
            "Duplication Road",
            "Baseline Road",
            "Union Place",
            "Marine Drive"
    );

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final StreetLightTypeFactory factory;

    private final StreetLightRepo repo;

    protected StreetLightService() {
        this.factory = null;
        this.repo = null;
    }

    @Inject
    public StreetLightService(StreetLightTypeFactory factory, StreetLightRepo repo) {
        this.factory = Objects.requireNonNull(factory, "Street Light Type Factory must not be Null!");
        this.repo = Objects.requireNonNull(repo, "Street Light Repo must not be Null!");
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