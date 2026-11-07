package com.thisal.smartcity360.pattern.flyweight.street_light_flyweight;

import com.thisal.smartcity360.record.StreetLightSpec;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class StreetLightTypeFactory {

    private final Map<StreetLightSpec, StreetLightType> cache = new ConcurrentHashMap<>();

    public StreetLightType getType(StreetLightSpec spec) {
        Objects.requireNonNull(spec, "Spec must not be Null!");
        return cache.computeIfAbsent(spec, StreetLightType::new);
    }

    public StreetLightType createUnshared(StreetLightSpec spec) {
        return new StreetLightType(Objects.requireNonNull(spec, "Spec must not be Null!"));
    }

    public int cacheTypeCount() {
        return cache.size();
    }

    public List<StreetLightType> cachedType() {
        return List.copyOf(cache.values());
    }

}