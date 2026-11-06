package com.thisal.smartcity360.record;

public record ScheduleReport(
        String time,
        int lightsOn,
        int lightsOff,
        double powerDrawKw
) {
}