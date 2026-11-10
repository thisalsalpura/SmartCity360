package com.thisal.smartcity360.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class NoiseMeasurementRequest {

    @PositiveOrZero(message = "Noise must be a Positive Number or Zero!")
    private double noise;

    @NotBlank(message = "Time is Required!")
    @Pattern(regexp = "([01]\\d|2[0-3]):[0-5]\\d", message = "Time must be in HH:mm Format!")
    private String time;

}