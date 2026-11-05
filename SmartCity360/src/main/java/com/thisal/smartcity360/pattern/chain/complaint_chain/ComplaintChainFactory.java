package com.thisal.smartcity360.pattern.chain.complaint_chain;

import com.thisal.smartcity360.enums.StaffLevel;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Objects;

public final class ComplaintChainFactory {

    private ComplaintChainFactory() {
    }

    public static ComplaintHandler create(Collection<StaffLevel> onDuty) {
        Objects.requireNonNull(onDuty, "OnDuty Levels must be not Null!");
        EnumSet<StaffLevel> levels = EnumSet.noneOf(StaffLevel.class);
        levels.addAll(onDuty);
        if (levels.isEmpty()) {
            throw new IllegalArgumentException("At least One Handler must be OnDuty!");
        }

        ComplaintHandler head = null;
        ComplaintHandler tail = null;
        for (StaffLevel level : StaffLevel.values()) {
            if (!levels.contains(level)) {
                continue;
            }
            ComplaintHandler handler = handlerFor(level);
            if (head == null) {
                head = handler;
            } else {
                tail.setNext(handler);
            }
            tail = handler;
        }
        return head;
    }

    private static ComplaintHandler handlerFor(StaffLevel level) {
        return switch (level) {
            case LOCAL_STAFF -> new LocalStaffHandler();
            case SUPERVISOR -> new SupervisorHandler();
            case CITY_MANAGER -> new CityManagerHandler();
        };
    }

}