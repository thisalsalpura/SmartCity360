package com.thisal.smartcity360.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ZoneLevel {

    CITY("City"),
    DISTRICT("District"),
    STREET("Street"),
    BUILDING("Building");

    private final String displayName;

}