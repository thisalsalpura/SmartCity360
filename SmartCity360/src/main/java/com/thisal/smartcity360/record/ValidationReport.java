package com.thisal.smartcity360.record;

public record ValidationReport(
        boolean valid,
        String canonical,
        String message,
        int position
) {
}