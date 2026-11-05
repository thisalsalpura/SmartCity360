package com.thisal.smartcity360.record;

import com.thisal.smartcity360.enums.ComplaintSeverity;

import java.time.Instant;
import java.util.Objects;

public record Complaint(
        String id,
        String residentName,
        String subject,
        String description,
        ComplaintSeverity severity,
        Instant submittedAt
) {

    public Complaint {
        Objects.requireNonNull(id, "ComplaintId must not be Null!");
        Objects.requireNonNull(severity, "Severity must not be Null!");
        Objects.requireNonNull(submittedAt, "Submitted Time must not be Null!");
        if (residentName == null || residentName.isBlank()) {
            throw new IllegalArgumentException("Resident Name must not be Null or Blank!");
        }
        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("Subject must not be Null or Blank!");
        }
        description = description == null
                ? ""
                : description;
    }

}