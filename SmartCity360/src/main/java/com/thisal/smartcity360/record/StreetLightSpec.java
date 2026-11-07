package com.thisal.smartcity360.record;

import com.thisal.smartcity360.enums.LampTechnology;

import java.time.LocalTime;
import java.util.Objects;

public record StreetLightSpec(
        String model,
        LampTechnology technology,
        int watts,
        int lumens,
        LocalTime defaultOn,
        LocalTime defaultOff
) {

    public StreetLightSpec {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model must not be Null or Blank!");
        }
        Objects.requireNonNull(technology, "Technology must not be Null!");
        if (watts <= 0) {
            throw new IllegalArgumentException("Watts must be Positive!");
        }
        if (lumens <= 0) {
            throw new IllegalArgumentException("Lumens must be Positive!");
        }
        Objects.requireNonNull(defaultOn, "DefaultOn Time must not be Null!");
        Objects.requireNonNull(defaultOff, "DefaultOff Time must not be Null!");
        if (defaultOn.equals(defaultOff)) {
            throw new IllegalArgumentException("DefaultOn Time and DefaultOff Time must be Different!");
        }
    }

}