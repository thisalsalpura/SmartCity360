package com.thisal.smartcity360.model;

import com.thisal.smartcity360.enums.ComplaintSeverity;
import com.thisal.smartcity360.enums.StaffLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ComplaintRequest {

    @NotBlank(message = "Resident Name is Required!")
    @Size(max = 60, message = "Resident Name must be at most 60 Characters!")
    private String residentName;

    @NotBlank(message = "Subject is Required!")
    @Size(max = 100, message = "Subject must be at most 100 Characters!")
    private String subject;

    @Size(max = 1000, message = "Description must be at most 1000 Characters!")
    private String description;

    @NotNull(message = "Severity is Required!")
    private ComplaintSeverity severity;

    private List<StaffLevel> onDuty = new ArrayList<>();

}