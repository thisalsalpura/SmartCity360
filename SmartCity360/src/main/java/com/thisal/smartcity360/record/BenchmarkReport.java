package com.thisal.smartcity360.record;

public record BenchmarkReport(
        int lights,
        int sharedTypeObjects,
        int unsharedTypeObjects,
        long estimatedSharedBytes,
        long estimatedUnsharedBytes,
        double savedPercent,
        double sharedBuildMillis,
        double unsharedBuildMillis,
        String note
) {
}