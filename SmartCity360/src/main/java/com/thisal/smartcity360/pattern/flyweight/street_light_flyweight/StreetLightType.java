package com.thisal.smartcity360.pattern.flyweight.street_light_flyweight;

import com.thisal.smartcity360.record.LightLocation;
import com.thisal.smartcity360.record.StreetLightSpec;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Objects;

@Getter
public final class StreetLightType {

    private final StreetLightSpec spec;

    StreetLightType(StreetLightSpec spec) {
        this.spec = Objects.requireNonNull(spec, "Spec must not be Null!");
    }

    public boolean shouldBeOn(LocalTime now) {
        Objects.requireNonNull(now, "Time must not be Null!");
        LocalTime on = spec.defaultOn();
        LocalTime off = spec.defaultOff();
        if (on.isBefore(off)) {
            return !now.isBefore(on) && now.isBefore(off);
        } else {
            return !now.isBefore(on) || now.isBefore(off);
        }
    }

    public int powerDrawWatts(boolean on) {
        return on
                ? spec.watts()
                : 0;
    }

    public String describe(LightLocation location, boolean on) {
        Objects.requireNonNull(location, "Location must not be Null!");
        return "| Model: " + spec.model() + " |" +
                "| Technology: " + spec.technology().getDisplayName() + " |" +
                "| Watts: " + spec.watts() + "W |" +
                "| Location: " + location.street() + " |" +
                "| Status: " + (on ? "ON" : "OFF") + " |";
    }

}