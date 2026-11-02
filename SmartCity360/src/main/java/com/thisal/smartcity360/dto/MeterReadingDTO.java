package com.thisal.smartcity360.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.Instant;

@Data
public class MeterReadingDTO {

    @NotNull(message = "Timestamp is Required!")
    private Instant timestamp;

    @PositiveOrZero(message = "KilowattHours must be a Positive Number or Zero!")
    private double kilowattHours;

}