package com.thisal.smartcity360.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PolicyRequest {

    @NotBlank(message = "Name is Required!")
    @Size(max = 60, message = "Name must be at most 60 Characters!")
    private String name;

    @Size(max = 200, message = "Description must be at most 200 Characters!")
    private String description;

    @NotBlank(message = "Rule is Required!")
    @Size(max = 500, message = "Rule must be at most 500 Characters!")
    private String rule;

}