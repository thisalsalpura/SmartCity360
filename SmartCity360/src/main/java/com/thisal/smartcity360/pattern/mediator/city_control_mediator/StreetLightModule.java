package com.thisal.smartcity360.pattern.mediator.city_control_mediator;

import com.thisal.smartcity360.enums.CityEventType;
import com.thisal.smartcity360.enums.EventField;
import com.thisal.smartcity360.record.CityEvent;
import com.thisal.smartcity360.record.ScheduleReport;
import com.thisal.smartcity360.service.StreetLightService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Objects;
import java.util.Set;

@ApplicationScoped
public class StreetLightModule implements CityModule {

    private final StreetLightService service;

    protected StreetLightModule() {
        this.service = null;
    }

    @Inject
    public StreetLightModule(StreetLightService service) {
        this.service = Objects.requireNonNull(service, "Street Light Service must not be Null!");
    }

    @Override
    public String name() {
        return "Street Light";
    }

    @Override
    public Set<CityEventType> subscriptions() {
        return Set.of(
                CityEventType.TIME_CHANGED
        );
    }

    @Override
    public String handle(CityEvent event, CityMediator mediator) {
        assert service != null;
        ScheduleReport report = service.applySchedule(event.time(EventField.TIME));
        mediator.publish(CityEvent.of(CityEventType.LIGHTS_UPDATED, name())
                .with(EventField.LIGHTS_ON, report.lightsOn())
                .with(EventField.LIGHTS_OFF, report.lightsOff()));
        return "At " + report.time() + " " + report.lightsOn() + " light(s) On and " + report.lightsOff() + " light(s) Off!";
    }

}