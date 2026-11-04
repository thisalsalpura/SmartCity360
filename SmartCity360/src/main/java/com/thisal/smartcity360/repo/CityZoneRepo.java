package com.thisal.smartcity360.repo;

import com.thisal.smartcity360.enums.ZoneLevel;
import com.thisal.smartcity360.pattern.composite.city_zone_composite.CityZone;
import com.thisal.smartcity360.pattern.composite.city_zone_composite.CityZoneFactory;
import com.thisal.smartcity360.pattern.composite.city_zone_composite.ZoneComposite;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class CityZoneRepo {

    private static final String ROOT_ID = "CITY-1";

    private final Map<String, CityZone> index = new HashMap<>();

    private final Map<ZoneLevel, AtomicInteger> counters = new ConcurrentHashMap<>();

    private CityZone root;

    @PostConstruct
    void init() {
        seed();
    }

    public CityZone root() {
        return root;
    }

    public Optional<CityZone> find(String id) {
        return Optional.ofNullable(index.get(id));
    }

    public boolean isRoot(String id) {
        return CityZoneRepo.ROOT_ID.equals(id);
    }

    public String nextId(ZoneLevel level) {
        int next = counters.computeIfAbsent(level, l -> new AtomicInteger()).incrementAndGet();
        return level.name() + "-" + next;
    }

    public void register(CityZone zone) {
        index.put(zone.getId(), zone);
    }

    public void unregister(CityZone zone) {
        zone.getChildren().forEach(this::unregister);
        index.remove(zone.getId());
    }

    private ZoneComposite composite(ZoneLevel level, String name, ZoneComposite parent) {
        ZoneComposite zone = (ZoneComposite) CityZoneFactory.create(level, nextId(level), name, 0.0);
        register(zone);
        if (parent != null) {
            parent.add(zone);
        }
        return zone;
    }

    private void building(ZoneComposite street, String name, double kilowattHours) {
        CityZone zone = CityZoneFactory.create(ZoneLevel.BUILDING, nextId(ZoneLevel.BUILDING), name, kilowattHours);
        register(zone);
        street.add(zone);
    }

    private void seed() {
        index.clear();
        counters.clear();

        ZoneComposite colomboCity = composite(ZoneLevel.CITY, "Colombo City", null);
        root = colomboCity;

        ZoneComposite colomboFort = composite(ZoneLevel.DISTRICT, "Colombo Fort", colomboCity);

        ZoneComposite yorkStreet = composite(ZoneLevel.STREET, "York Street", colomboFort);
        building(yorkStreet, "Central Library", 420.5);
        building(yorkStreet, "Central Bank", 970.0);

        ZoneComposite chathamStreet = composite(ZoneLevel.STREET, "Chatham Street", colomboFort);
        building(chathamStreet, "Town Hall", 460.25);

        ZoneComposite pettah = composite(ZoneLevel.DISTRICT, "Pettah", colomboCity);

        ZoneComposite mainStreet = composite(ZoneLevel.STREET, "Main Street", pettah);
        building(mainStreet, "Market", 770.75);
        building(mainStreet, "Textile Complex", 524.5);
        building(mainStreet, "Vehicle Park", 110.0);
    }

}