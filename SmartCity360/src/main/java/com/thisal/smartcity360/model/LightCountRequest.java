package com.thisal.smartcity360.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class LightCountRequest {

    @Min(value = 1, message = "Count must be at least 1!")
    @Max(value = 100000, message = "Count must be at most 100000!")
    private int count;

}