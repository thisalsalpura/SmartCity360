package com.thisal.smartcity360.pattern.composite.city_zone_composite;

import com.thisal.smartcity360.enums.ZoneLevel;

public class District extends ZoneComposite {

    public District(String id, String name) {
        super(id, name);
    }

    @Override
    public ZoneLevel getLevel() {
        return ZoneLevel.DISTRICT;
    }

    @Override
    protected ZoneLevel childLevel() {
        return ZoneLevel.STREET;
    }

}