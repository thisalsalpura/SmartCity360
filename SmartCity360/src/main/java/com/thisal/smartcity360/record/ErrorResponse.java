package com.thisal.smartcity360.record;

public record ErrorResponse(
        int status,
        String message
) {
}