package com.thisal.smartcity360.pattern.chain.complaint_chain;

import com.thisal.smartcity360.enums.ComplaintSeverity;
import com.thisal.smartcity360.enums.StaffLevel;
import com.thisal.smartcity360.record.Complaint;

public class LocalStaffHandler extends ComplaintHandler {

    @Override
    public StaffLevel level() {
        return StaffLevel.LOCAL_STAFF;
    }

    @Override
    public ComplaintSeverity maxSeverity() {
        return ComplaintSeverity.LOW;
    }

    @Override
    protected String resolutionNote(Complaint complaint) {
        return "Logged and Resolved at the Local Service Desk!";
    }

}