package com.thisal.smartcity360.record;

import com.thisal.smartcity360.enums.DeliveryOutcome;

import java.util.List;

public record TransmissionReport(
        String meterId,
        String pipeline,
        int readingCount,
        int originalBytes,
        int wireBytes,
        List<StageSize> stages,
        DeliveryOutcome outcome,
        String message,
        String receivedPreview
) {
}