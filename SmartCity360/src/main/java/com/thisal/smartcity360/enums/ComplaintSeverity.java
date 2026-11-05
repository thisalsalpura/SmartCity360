package com.thisal.smartcity360.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ComplaintSeverity {

    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    CRITICAL("Critical");

    private final String displayName;

    public boolean exceeds(ComplaintSeverity limit) {
        return this.compareTo(limit) > 0;
    }

}