package com.thisal.smartcity360.record;

public record HandlerInfo(
        String name,
        String displayName,
        String description,
        String maxSeverity,
        int order
) {
}