package com.thisal.smartcity360.pattern.composite.city_zone_composite;

import com.thisal.smartcity360.enums.ZoneLevel;

public abstract class AbstractCityZone implements CityZone {

    private final String id;

    private final String name;

    protected AbstractCityZone(String id, String name) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("CityZone Id must not be Null or Blank!");
        } else if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("CityZone Name must not be Null or Blank!");
        }
        this.id = id;
        this.name = name.trim();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public abstract ZoneLevel getLevel();

}