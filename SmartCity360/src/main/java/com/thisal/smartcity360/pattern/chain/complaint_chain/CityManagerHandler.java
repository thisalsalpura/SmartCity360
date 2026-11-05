package com.thisal.smartcity360.pattern.chain.complaint_chain;

import com.thisal.smartcity360.enums.ComplaintSeverity;
import com.thisal.smartcity360.enums.StaffLevel;
import com.thisal.smartcity360.record.Complaint;

public class CityManagerHandler extends ComplaintHandler {

    @Override
    public StaffLevel level() {
        return StaffLevel.CITY_MANAGER;
    }

    @Override
    public ComplaintSeverity maxSeverity() {
        return ComplaintSeverity.CRITICAL;
    }

    @Override
    protected String resolutionNote(Complaint complaint) {
        return "City Manager approved an Emergency Response.";
    }

}