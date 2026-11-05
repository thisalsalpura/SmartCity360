package com.thisal.smartcity360.pattern.chain.complaint_chain;

import com.thisal.smartcity360.enums.ComplaintSeverity;
import com.thisal.smartcity360.enums.HandlingAction;
import com.thisal.smartcity360.enums.StaffLevel;
import com.thisal.smartcity360.record.Complaint;
import com.thisal.smartcity360.record.HandlingStep;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class ComplaintHandler {

    private ComplaintHandler next;

    public ComplaintHandler setNext(ComplaintHandler next) {
        this.next = Objects.requireNonNull(next, "Next Handler must not be Null!");
        return next;
    }

    public final Optional<StaffLevel> handle(Complaint complaint, List<HandlingStep> trail) {
        Objects.requireNonNull(complaint, "Complaint must not be Null!");
        Objects.requireNonNull(trail, "Trail must not be Null!");
        String name = level().getDisplayName();

        if (!complaint.severity().exceeds(maxSeverity())) {
            trail.add(new HandlingStep(
                    name,
                    HandlingAction.HANDLED,
                    resolutionNote(complaint)
            ));
            return Optional.of(level());
        } else if (next == null) {
            trail.add(new HandlingStep(
                    name,
                    HandlingAction.UNHANDLED,
                    complaint.severity().getDisplayName() + " is above this Authority and no Higher Handlers is on Duty!"
            ));
            return Optional.empty();
        } else {
            trail.add(new HandlingStep(
                    name,
                    HandlingAction.ESCALATED,
                    complaint.severity().getDisplayName() + " is above this Authority up to " + maxSeverity().getDisplayName() + ". Passed to " + next.level().getDisplayName() + "."
            ));
            return next.handle(complaint, trail);
        }
    }

    public final String describe() {
        return next == null
                ? level().getDisplayName()
                : level().getDisplayName() + " -> " + next.describe();
    }

    public abstract StaffLevel level();

    public abstract ComplaintSeverity maxSeverity();

    protected abstract String resolutionNote(Complaint complaint);

}