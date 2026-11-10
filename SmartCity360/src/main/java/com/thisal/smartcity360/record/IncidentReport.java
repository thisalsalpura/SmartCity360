package com.thisal.smartcity360.record;

import java.util.List;

public record IncidentReport(
        String scenario,
        int eventsProcessed,
        List<ControlLogEntry> log,
        String summary
) {
}