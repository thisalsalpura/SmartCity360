package com.thisal.smartcity360.record;

import com.thisal.smartcity360.enums.CityEventType;

public record ControlLogEntry(
        int step,
        String source,
        CityEventType event,
        String handledBy,
        String outcome
) {
}