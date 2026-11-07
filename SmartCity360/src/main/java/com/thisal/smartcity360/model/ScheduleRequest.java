package com.thisal.smartcity360.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ScheduleRequest {

    @NotBlank(message = "Time is Required!")
    @Pattern(regexp = "([01][0-9]|2[0-3]):[0-5][0-9]", message = "Time must be in HH:mm Format!")
    private String time;

}