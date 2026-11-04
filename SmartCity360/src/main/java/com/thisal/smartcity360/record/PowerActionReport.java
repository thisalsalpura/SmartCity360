package com.thisal.smartcity360.record;

import com.thisal.smartcity360.enums.ZoneLevel;

public record PowerActionReport(
        String zoneId,
        String zoneName,
        ZoneLevel level,
        String action,
        int buildingsAffected,
        double kilowattHoursAfter
) {
}