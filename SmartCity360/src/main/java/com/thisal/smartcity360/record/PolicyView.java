package com.thisal.smartcity360.record;

public record PolicyView(
        String id,
        String name,
        String description,
        String rule,
        String canonical
) {
}