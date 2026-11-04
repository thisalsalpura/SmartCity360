package com.thisal.smartcity360.pattern.composite.city_zone_composite;

import com.thisal.smartcity360.enums.ZoneLevel;
import com.thisal.smartcity360.exception.InvalidZoneOperationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class ZoneComposite extends AbstractCityZone {

    private final List<CityZone> children = new ArrayList<>();

    protected ZoneComposite(String id, String name) {
        super(id, name);
    }

    protected abstract ZoneLevel childLevel();

    public void add(CityZone child) {
        Objects.requireNonNull(child, "Child CityZone must not be Null!");
        if (child.getLevel() != childLevel()) {
            throw new InvalidZoneOperationException(getLevel().getDisplayName() + " can only contain " + childLevel().getDisplayName() + " Zone!");
        }
        children.add(child);
    }

    public boolean remove(String childId) {
        return children.removeIf(child -> child.getId().equals(childId));
    }

    @Override
    public int shutdownPower() {
        int affected = 0;
        for (CityZone child : children) {
            affected += child.shutdownPower();
        }
        return affected;
    }

    @Override
    public int restorePower() {
        int affected = 0;
        for (CityZone child : children) {
            affected += child.restorePower();
        }
        return affected;
    }

    @Override
    public double reportUsage() {
        double total = 0.0;
        for (CityZone child : children) {
            total += child.reportUsage();
        }
        return total;
    }

    @Override
    public boolean isPoweredOn() {
        return children.stream().anyMatch(CityZone::isPoweredOn);
    }

    @Override
    public List<CityZone> getChildren() {
        return Collections.unmodifiableList(children);
    }

}