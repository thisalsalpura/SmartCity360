package com.thisal.smartcity360.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StaffLevel {

    LOCAL_STAFF("Local Staff", "First contact at the District Service Desk. Handle routine Complaints."),
    SUPERVISOR("Supervisor", "Handles Complaints that need a Field Team or a Budget Decisions."),
    CITY_MANAGER("City Manager", "Final Authority. Handles Emergencies and Anything Escalated to the Top.");

    private final String displayName;
    private final String description;

}