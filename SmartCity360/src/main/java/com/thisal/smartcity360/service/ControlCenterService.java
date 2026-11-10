package com.thisal.smartcity360.service;

import com.thisal.smartcity360.enums.CityEventType;
import com.thisal.smartcity360.enums.EventField;
import com.thisal.smartcity360.pattern.mediator.city_control_mediator.CityControlCenter;
import com.thisal.smartcity360.record.CityEvent;
import com.thisal.smartcity360.record.IncidentReport;
import com.thisal.smartcity360.record.ModuleInfo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ControlCenterService {

    private static final String OPERATOR = "Operator";

    private final CityControlCenter controlCenter;

    protected ControlCenterService() {
        this.controlCenter = null;
    }

    @Inject
    public ControlCenterService(CityControlCenter controlCenter) {
        this.controlCenter = Objects.requireNonNull(controlCenter, "Control Center must not be Null!");
    }

    public List<ModuleInfo> modules() {
        assert controlCenter != null;
        return controlCenter.modules().stream()
                .map(module -> new ModuleInfo(
                        module.name(),
                        module.subscriptions().stream().sorted(Comparator.naturalOrder()).map(Enum::name).toList()
                )).toList();
    }

    public IncidentReport reportOutage(String zoneId) {
        assert controlCenter != null;
        return controlCenter.run(
                "Power Outage",
                CityEvent.of(CityEventType.OUTAGE_REPORTED, ControlCenterService.OPERATOR)
                        .with(EventField.ZONE_ID, zoneId)
        );
    }

    public IncidentReport measureNoise(double noise, LocalTime time) {
        assert controlCenter != null;
        return controlCenter.run(
                "Noise Measurement",
                CityEvent.of(CityEventType.NOISE_MEASURED, ControlCenterService.OPERATOR)
                        .with(EventField.NOISE, noise)
                        .with(EventField.TIME, time)
        );
    }

    public IncidentReport changeTime(LocalTime time) {
        assert controlCenter != null;
        return controlCenter.run(
                "City Clock Change",
                CityEvent.of(CityEventType.TIME_CHANGED, ControlCenterService.OPERATOR)
                        .with(EventField.TIME, time)
        );
    }

}