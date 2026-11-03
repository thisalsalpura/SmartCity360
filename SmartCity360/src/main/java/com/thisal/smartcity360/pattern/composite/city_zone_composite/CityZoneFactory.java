package com.thisal.smartcity360.pattern.composite.city_zone_composite;

import com.thisal.smartcity360.enums.ZoneLevel;

public final class CityZoneFactory {

    private CityZoneFactory() {
    }

    public static CityZone create(ZoneLevel level, String id, String name, double kilowattHours) {
        return switch (level) {
            case CITY -> new City(id, name);
            case DISTRICT -> new District(id, name);
            case STREET -> new Street(id, name);
            case BUILDING -> new Building(id, name, kilowattHours);
        };
    }

}