package com.thisal.smartcity360.record;

import com.thisal.smartcity360.enums.ComplaintSeverity;
import com.thisal.smartcity360.enums.ComplaintStatus;

import java.time.Instant;
import java.util.List;

public record ComplaintReport(
        String id,
        String residentName,
        String subject,
        ComplaintSeverity severity,
        ComplaintStatus status,
        String resolvedBy,
        String chain,
        List<HandlingStep> trail,
        String message,
        Instant submittedAt
) {
}