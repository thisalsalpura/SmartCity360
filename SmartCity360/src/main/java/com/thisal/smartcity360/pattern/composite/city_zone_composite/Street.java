package com.thisal.smartcity360.pattern.composite.city_zone_composite;

import com.thisal.smartcity360.enums.ZoneLevel;

public class Street extends ZoneComposite {

    public Street(String id, String name) {
        super(id, name);
    }

    @Override
    public ZoneLevel getLevel() {
        return ZoneLevel.STREET;
    }

    @Override
    protected ZoneLevel childLevel() {
        return ZoneLevel.BUILDING;
    }

}