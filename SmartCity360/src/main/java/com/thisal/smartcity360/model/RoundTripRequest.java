package com.thisal.smartcity360.model;

import com.thisal.smartcity360.dto.MeterReadingDTO;
import com.thisal.smartcity360.enums.StreamLayer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoundTripRequest {

    @NotBlank(message = "MeterId is Required!")
    @Pattern(regexp = "[A-Za-z0-9_-]{1,40}", message = "MeterId may contain only Letters, Digits, Hyphens and Underscores! Max characters limit is 40.")
    private String meterId;

    @NotEmpty(message = "At least One Meter Reading is Required!")
    @Size(max = 5000, message = "Maximum 5000 Meter Readings are Allowed!")
    private List<@Valid MeterReadingDTO> readings;

    private List<StreamLayer> layers = new ArrayList<>();

    private boolean tamper;

}