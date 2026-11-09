package com.thisal.smartcity360.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RuleCheckRequest {

    @NotBlank(message = "Rule is Required!")
    @Size(max = 200, message = "Rule must be at most 500 Characters!")
    private String rule;

}