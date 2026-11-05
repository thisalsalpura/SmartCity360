package com.thisal.smartcity360.pattern.chain.complaint_chain;

import com.thisal.smartcity360.enums.ComplaintSeverity;
import com.thisal.smartcity360.enums.StaffLevel;
import com.thisal.smartcity360.record.Complaint;

public class SupervisorHandler extends ComplaintHandler {

    @Override
    public StaffLevel level() {
        return StaffLevel.SUPERVISOR;
    }

    @Override
    public ComplaintSeverity maxSeverity() {
        return ComplaintSeverity.MEDIUM;
    }

    @Override
    protected String resolutionNote(Complaint complaint) {
        return "Reviewed by the Supervisor and a Field Team was Assigned.";
    }

}