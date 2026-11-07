package com.thisal.smartcity360.record;

import java.util.List;

public record FleetSummary(
        int totalLights,
        int sharedTypeObjects,
        int lightsOn,
        double powerDrawKw,
        List<StreetLightView> sample
) {
}