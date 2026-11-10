package com.thisal.smartcity360.record;

import java.util.List;

public record ModuleInfo(
        String name,
        List<String> subscriptions
) {
}