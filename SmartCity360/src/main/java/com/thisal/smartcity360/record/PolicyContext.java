package com.thisal.smartcity360.record;

import com.thisal.smartcity360.enums.PolicyVariable;
import com.thisal.smartcity360.exception.PolicyEvaluationException;

import java.time.LocalTime;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public record PolicyContext(
        Map<PolicyVariable, Double> values,
        LocalTime time
) {

    public PolicyContext {
        Objects.requireNonNull(time, "Time must not be Null!");
        EnumMap<PolicyVariable, Double> copy = new EnumMap<>(PolicyVariable.class);
        if (values != null) {
            values.forEach(((variable, value) -> {
                Objects.requireNonNull(variable, "Variable must not be Null!");
                if (value == null || value.isNaN() || value.isInfinite()) {
                    throw new IllegalArgumentException(variable.name() + " value must be a Finite Number!");
                }
                copy.put(variable, value);
            }));
        }
        values = Collections.unmodifiableMap(copy);
    }

    public double valueOf(PolicyVariable variable) {
        Double value = values.get(variable);
        if (value == null) {
            throw new PolicyEvaluationException("No value was supplied for " + variable.name() + "!");
        }
        return value;
    }

}