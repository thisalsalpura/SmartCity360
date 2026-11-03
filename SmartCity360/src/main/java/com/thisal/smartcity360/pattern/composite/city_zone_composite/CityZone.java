package com.thisal.smartcity360.pattern.composite.city_zone_composite;

import com.thisal.smartcity360.enums.ZoneLevel;

import java.util.List;

public interface CityZone {

    String getId();

    String getName();

    ZoneLevel getLevel();

    int shutdownPower();

    int restorePower();

    double reportUsage();

    boolean isPoweredOn();

    List<CityZone> getChildren();

}