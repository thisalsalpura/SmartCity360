package com.thisal.smartcity360.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LampTechnology {

    LED("LED"),
    SODIUM("High-Pressure Sodium"),
    METAL_HALIDE("Metal Halide");

    private final String displayName;

}