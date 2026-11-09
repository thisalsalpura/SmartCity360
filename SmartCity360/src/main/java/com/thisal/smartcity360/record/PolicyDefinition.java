package com.thisal.smartcity360.record;

import com.thisal.smartcity360.pattern.interpreter.policy_interpreter.PolicyExpression;

import java.util.Objects;

public record PolicyDefinition(
        String id,
        String name,
        String description,
        String rule,
        PolicyExpression expression
) {

    public PolicyDefinition {
        Objects.requireNonNull(id, "ID must not be Null!");
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be Null or Blank!");
        }
        Objects.requireNonNull(expression, "Expression must not be Null!");
        description = description == null
                ? ""
                : description;
    }

}