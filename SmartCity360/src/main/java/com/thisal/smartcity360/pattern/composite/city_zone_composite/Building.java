package com.thisal.smartcity360.pattern.composite.city_zone_composite;

import com.thisal.smartcity360.enums.ZoneLevel;

import java.util.List;

public class Building extends AbstractCityZone {

    private final double kilowattHours;

    private boolean poweredOn = true;

    public Building(String id, String name, double kilowattHours) {
        super(id, name);
        if (Double.isNaN(kilowattHours) || Double.isInfinite(kilowattHours) || kilowattHours < 0) {
            throw new IllegalArgumentException("KilowattHours must be a Non-Negative Finite Number!");
        }
        this.kilowattHours = kilowattHours;
    }

    @Override
    public ZoneLevel getLevel() {
        return ZoneLevel.BUILDING;
    }

    @Override
    public int shutdownPower() {
        if (!poweredOn) {
            return 0;
        }
        poweredOn = false;
        return 1;
    }

    @Override
    public int restorePower() {
        if (poweredOn) {
            return 0;
        }
        poweredOn = true;
        return 1;
    }

    @Override
    public double reportUsage() {
        return poweredOn
                ? kilowattHours
                : 0.0;
    }

    @Override
    public boolean isPoweredOn() {
        return poweredOn;
    }

    @Override
    public List<CityZone> getChildren() {
        return List.of();
    }

}