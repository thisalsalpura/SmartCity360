package com.thisal.smartcity360.record;

public record StreetLightTypeInfo(
        String model,
        String technology,
        int watts,
        int lumens,
        String schedule,
        long lightCount
) {
}