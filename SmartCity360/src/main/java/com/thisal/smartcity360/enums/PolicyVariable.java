package com.thisal.smartcity360.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PolicyVariable {

    NOISE("Ambient street noise Level.", "dB"),
    PARKING("Vehicle parking Duration.", "Hours"),
    SPEED("Vehicle Speed.", "km/h");

    private final String description;
    private final String unit;

}