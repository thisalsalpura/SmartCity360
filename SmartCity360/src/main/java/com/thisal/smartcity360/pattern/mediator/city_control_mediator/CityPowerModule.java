package com.thisal.smartcity360.pattern.mediator.city_control_mediator;

import com.thisal.smartcity360.enums.CityEventType;
import com.thisal.smartcity360.enums.EventField;
import com.thisal.smartcity360.record.CityEvent;
import com.thisal.smartcity360.record.PowerActionReport;
import com.thisal.smartcity360.service.CityZoneService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Objects;
import java.util.Set;

@ApplicationScoped
public class CityPowerModule implements CityModule {

    private final CityZoneService service;

    protected CityPowerModule() {
        this.service = null;
    }

    @Inject
    public CityPowerModule(CityZoneService service) {
        this.service = Objects.requireNonNull(service, "CityZone Service must not be Null!");
    }

    @Override
    public String name() {
        return "Zone Power";
    }

    @Override
    public Set<CityEventType> subscriptions() {
        return Set.of(
                CityEventType.OUTAGE_REPORTED
        );
    }

    @Override
    public String handle(CityEvent event, CityMediator mediator) {
        assert service != null;
        PowerActionReport report = service.shutdownPower(event.text(EventField.ZONE_ID));
        mediator.publish(CityEvent.of(CityEventType.POWER_SHUTDOWN, name())
                .with(EventField.ZONE_ID, report.zoneId())
                .with(EventField.ZONE_NAME, report.zoneName())
                .with(EventField.BUILDINGS_AFFECTED, report.buildingsAffected()));
        return "Shutdown the " + report.zoneName() + " and " + report.buildingsAffected() + " building(s) Switched Off!";
    }

}