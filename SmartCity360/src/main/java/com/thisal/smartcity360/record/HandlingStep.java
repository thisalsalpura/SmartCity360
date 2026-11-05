package com.thisal.smartcity360.record;

import com.thisal.smartcity360.enums.HandlingAction;

public record HandlingStep(
        String handler,
        HandlingAction action,
        String note
) {
}