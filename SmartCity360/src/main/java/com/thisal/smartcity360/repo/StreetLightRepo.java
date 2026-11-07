package com.thisal.smartcity360.repo;

import com.thisal.smartcity360.pattern.flyweight.street_light_flyweight.StreetLight;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class StreetLightRepo {

    private final List<StreetLight> lights = new ArrayList<>();

    private int lastId;

    public int nextId() {
        return ++lastId;
    }

    public void add(StreetLight light) {
        lights.add(light);
    }

    public List<StreetLight> all() {
        return Collections.unmodifiableList(lights);
    }

    public int size() {
        return lights.size();
    }

    public void clear() {
        lights.clear();
        lastId = 0;
    }

}