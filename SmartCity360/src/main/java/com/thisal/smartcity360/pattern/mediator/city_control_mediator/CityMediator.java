package com.thisal.smartcity360.pattern.mediator.city_control_mediator;

import com.thisal.smartcity360.record.CityEvent;

public interface CityMediator {

    void publish(CityEvent event);

}