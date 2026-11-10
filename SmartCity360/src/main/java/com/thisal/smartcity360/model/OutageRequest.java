package com.thisal.smartcity360.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OutageRequest {

    @NotBlank(message = "ZoneId is Required!")
    private String zoneId;

}