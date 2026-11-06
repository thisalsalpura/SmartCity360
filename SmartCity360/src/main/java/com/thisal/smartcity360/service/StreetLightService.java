package com.thisal.smartcity360.service;

import com.thisal.smartcity360.enums.LampTechnology;
import com.thisal.smartcity360.exception.InvalidFleetOperationException;
import com.thisal.smartcity360.pattern.flyweight.street_light_flyweight.StreetLight;
import com.thisal.smartcity360.pattern.flyweight.street_light_flyweight.StreetLightTypeFactory;
import com.thisal.smartcity360.record.*;
import com.thisal.smartcity360.repo.StreetLightRepo;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

    private static StreetLightView toView(StreetLight light) {
        return new StreetLightView(
                light.getId(),
                light.getType().getSpec().model(),
                light.getLocation().street(),
                light.getLocation().latitude(),
                light.getLocation().longitude(),
                light.isOn(),
                light.powerDrawWatts()
        );
    }

    private static LightLocation randomLocation(ThreadLocalRandom random) {
        String street = StreetLightService.STREETS.get(random.nextInt(StreetLightService.STREETS.size()));
        return new LightLocation(
                street,
                6.9271 + (random.nextDouble() - 0.5) * 0.04,
                79.8612 + (random.nextDouble() - 0.5) * 0.04
        );
    }

    private static double kilowatts(long watts) {
        return Math.round(watts / 10.0) / 100.0;
    }

    private static double millis(long nanos) {
        return Math.round(nanos / 10_000.0) / 100.0;
    }

    @PostConstruct
    void init() {
        write(() -> {
            return null;
        });
    }

    public List<StreetLightTypeInfo> types() {
        return read(() -> {
            assert repo != null;
            Map<StreetLightSpec, Long> usage = repo.all().stream()
                    .collect(Collectors.groupingBy(light -> light.getType().getSpec(), Collectors.counting()));
            List<StreetLightTypeInfo> infos = new ArrayList<>();
            for (StreetLightSpec spec : StreetLightService.CATALOG) {
                infos.add(new StreetLightTypeInfo(
                        spec.model(),
                        spec.technology().getDisplayName(),
                        spec.watts(),
                        spec.lumens(),
                        spec.defaultOn() + " - " + spec.defaultOff(),
                        usage.getOrDefault(spec, 0L)
                ));
            }
            return infos;
        });
    }

    public FleetSummary fleet(int limit) {
        return read(() -> summary(limit));
    }

    public FleetSummary generate(int count) {
        return write(() -> {
            assert repo != null;
            if (repo.size() + count > StreetLightService.MAX_FLEET) {
                throw new InvalidFleetOperationException("Fleet cannot exceed " + StreetLightService.MAX_FLEET + " Street Lights!");
            }
            addLight(count);
            return summary(StreetLightService.DEFAULT_SAMPLE);
        });
    }

    public FleetSummary reset() {
        return write(() -> {
            assert repo != null;
            repo.clear();
            addLight(StreetLightService.SEED_LIGHTS);
            return summary(StreetLightService.DEFAULT_SAMPLE);
        });
    }

    private void addLight(int count) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < count; i++) {
            StreetLightSpec spec = StreetLightService.CATALOG.get(random.nextInt(StreetLightService.CATALOG.size()));
            assert repo != null;
            assert factory != null;
            repo.add(new StreetLight(repo.nextId(), factory.getType(spec), randomLocation(random)));
        }
    }

    private FleetSummary summary(int limit) {
        int sampleSize = Math.clamp(
                limit <= 0
                        ? StreetLightService.DEFAULT_SAMPLE
                        : limit, 1,
                StreetLightService.MAX_SAMPLE
        );
        int on = 0;
        int watts = 0;
        assert repo != null;
        for (StreetLight light : repo.all()) {
            if (light.isOn()) {
                on++;
            }
            watts += light.powerDrawWatts();
        }
        List<StreetLightView> sample = repo.all().stream()
                .limit(sampleSize)
                .map(StreetLightService::toView)
                .toList();
        assert factory != null;
        return new FleetSummary(
                repo.size(),
                factory.cacheTypeCount(),
                on,
                kilowatts(watts),
                sample
        );
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