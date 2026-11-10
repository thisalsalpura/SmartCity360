package com.thisal.smartcity360.pattern.mediator.city_control_mediator;

import com.thisal.smartcity360.enums.CityEventType;
import com.thisal.smartcity360.enums.ComplaintSeverity;
import com.thisal.smartcity360.enums.EventField;
import com.thisal.smartcity360.record.CityEvent;
import com.thisal.smartcity360.record.ComplaintReport;
import com.thisal.smartcity360.service.ComplaintService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Objects;
import java.util.Set;

@ApplicationScoped
public class ComplaintDeskModule implements CityModule {

    private static final String RESIDENT = "City Control Center";

    private static final int CRITICAL_BUILDINGS = 5;

    private static final int HIGH_BUILDINGS = 3;

    private static final int MEDIUM_BUILDINGS = 2;

    private final ComplaintService service;

    protected ComplaintDeskModule() {
        this.service = null;
    }

    @Inject
    public ComplaintDeskModule(ComplaintService service) {
        this.service = Objects.requireNonNull(service, "Complaint Service must not be Null!");
    }

    private static ComplaintSeverity severityFor(int buildingsAffected) {
        if (buildingsAffected >= ComplaintDeskModule.CRITICAL_BUILDINGS) {
            return ComplaintSeverity.CRITICAL;
        } else if (buildingsAffected >= ComplaintDeskModule.HIGH_BUILDINGS) {
            return ComplaintSeverity.HIGH;
        } else if (buildingsAffected >= ComplaintDeskModule.MEDIUM_BUILDINGS) {
            return ComplaintSeverity.MEDIUM;
        } else {
            return ComplaintSeverity.LOW;
        }
    }

    @Override
    public String name() {
        return "Complaint Desk";
    }

    @Override
    public Set<CityEventType> subscriptions() {
        return Set.of(
                CityEventType.POWER_SHUTDOWN,
                CityEventType.POLICY_VIOLATION
        );
    }

    @Override
    public String handle(CityEvent event, CityMediator mediator) {
        return switch (event.type()) {
            case POWER_SHUTDOWN -> fromShutdown(event, mediator);
            case POLICY_VIOLATION -> fromViolation(event, mediator);
            default -> "Event Ignored!";
        };
    }

    private String fromShutdown(CityEvent event, CityMediator mediator) {
        int affected = event.integer(EventField.BUILDINGS_AFFECTED);
        if (affected == 0) {
            return "No Building was Affected! So no complaint was Filed.";
        }
        String zone = event.text(EventField.ZONE_NAME);
        return file(
                mediator,
                "Power shutdown in " + zone + ".",
                affected + " Building(s) lost power in " + zone + ".",
                severityFor(affected)
        );
    }

    private String fromViolation(CityEvent event, CityMediator mediator) {
        String policy = event.text(EventField.POLICY_NAME);
        return file(
                mediator,
                policy + " Policy Violation.",
                event.text(EventField.DETAIL),
                ComplaintSeverity.MEDIUM
        );
    }

    private String file(CityMediator mediator, String subject, String description, ComplaintSeverity severity) {
        assert service != null;
        ComplaintReport report = service.submit(ComplaintDeskModule.RESIDENT, subject, description, severity, null);
        mediator.publish(CityEvent.of(CityEventType.COMPLAINT_FILED, name())
                .with(EventField.COMPLAINT_ID, report.id())
                .with(EventField.COMPLAINT_STATUS, report.status()));
        return "Filed " + report.id() + " as " + severity.getDisplayName() + "! " + report.message() + ".";
    }

}