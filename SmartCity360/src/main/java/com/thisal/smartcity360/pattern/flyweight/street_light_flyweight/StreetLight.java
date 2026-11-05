package com.thisal.smartcity360.pattern.flyweight.street_light_flyweight;

import com.thisal.smartcity360.record.LightLocation;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Objects;

@Getter
public class StreetLight {

    private final int id;

    private final StreetLightType type;

    private final LightLocation location;

    private boolean on;

    public StreetLight(int id, StreetLightType type, LightLocation location) {
        this.id = id;
        this.type = Objects.requireNonNull(type, "Type must not be Null!");
        this.location = Objects.requireNonNull(location, "Location must not be Null!");
    }

    public void switchTo(boolean on) {
        this.on = on;
    }

    public void applySchedule(LocalTime now) {
        this.on = type.shouldBeOn(now);
    }

    public int powerDrawWatts() {
        return type.powerDrawWatts(on);
    }

    public String describe() {
        return type.describe(location, on);
    }

}