package com.thisal.smartcity360.model;

import com.thisal.smartcity360.enums.ZoneLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ZoneCreateRequest {

    @NotBlank(message = "ParentId is Required!")
    private String parentId;

    @NotBlank(message = "Name is Required!")
    @Size(max = 60, message = "Name must be at most 60 Characters!")
    private String name;

    @NotNull(message = "Level is Required!")
    private ZoneLevel level;

    @PositiveOrZero(message = "KilowattHours must be a Positive Number or Zero!")
    private double kilowattHours;

}