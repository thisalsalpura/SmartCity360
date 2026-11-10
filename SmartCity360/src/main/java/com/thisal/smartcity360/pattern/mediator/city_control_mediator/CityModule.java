package com.thisal.smartcity360.pattern.mediator.city_control_mediator;

import com.thisal.smartcity360.enums.CityEventType;
import com.thisal.smartcity360.record.CityEvent;

import java.util.Set;

public interface CityModule {

    String name();

    Set<CityEventType> subscriptions();

    String handle(CityEvent event, CityMediator mediator);

}