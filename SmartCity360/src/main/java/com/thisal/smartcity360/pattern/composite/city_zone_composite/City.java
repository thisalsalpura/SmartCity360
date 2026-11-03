package com.thisal.smartcity360.pattern.composite.city_zone_composite;

import com.thisal.smartcity360.enums.ZoneLevel;

public class City extends ZoneComposite {

    public City(String id, String name) {
        super(id, name);
    }

    @Override
    public ZoneLevel getLevel() {
        return ZoneLevel.CITY;
    }

    @Override
    protected ZoneLevel childLevel() {
        return ZoneLevel.DISTRICT;
    }

}