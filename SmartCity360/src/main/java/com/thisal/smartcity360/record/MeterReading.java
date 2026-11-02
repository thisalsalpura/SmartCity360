package com.thisal.smartcity360.record;

import java.time.Instant;
import java.util.Objects;

public record MeterReading(Instant timestamp, double kilowattHours) {

    public MeterReading {
        Objects.requireNonNull(timestamp, "Timestamp must not be Null!");
        if (Double.isNaN(kilowattHours) || Double.isInfinite(kilowattHours) || kilowattHours < 0) {
            throw new IllegalArgumentException("Kilowatt Hours must be a non-negative finite Number!");
        }
    }

}