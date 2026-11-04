package com.thisal.smartcity360.record;

import com.thisal.smartcity360.enums.ZoneLevel;

import java.util.List;

public record ZoneView(
        String id,
        String name,
        ZoneLevel level,
        double kilowattHours,
        boolean poweredOn,
        List<ZoneView> children
) {
}