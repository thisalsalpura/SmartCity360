package com.thisal.smartcity360.record;

public record StreetLightView(
        int id,
        String model,
        String street,
        double latitude,
        double longitude,
        boolean on,
        int powerDrawWatts
) {
}