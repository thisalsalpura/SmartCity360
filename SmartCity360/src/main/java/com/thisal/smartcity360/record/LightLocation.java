package com.thisal.smartcity360.record;

public record LightLocation(
        String street,
        double latitude,
        double longitude
) {

    public LightLocation {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street must not be Null or Blank!");
        }
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Latitude and Longitude is Out of Range!");
        }
    }

}