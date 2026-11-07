package com.thisal.smartcity360.pattern.interpreter.policy_interpreter;

import com.thisal.smartcity360.enums.ComparisonOperator;
import com.thisal.smartcity360.enums.PolicyVariable;
import com.thisal.smartcity360.record.PolicyContext;

import java.util.Objects;

public class ComparisonExpression implements PolicyExpression {

    private final PolicyVariable variable;

    private final ComparisonOperator operator;

    private final double limit;

    public ComparisonExpression(PolicyVariable variable, ComparisonOperator operator, double limit) {
        this.variable = Objects.requireNonNull(variable, "Variable must not be Null!");
        this.operator = Objects.requireNonNull(operator, "Operator must not be Null!");
        if (Double.isNaN(limit) || Double.isInfinite(limit)) {
            throw new IllegalArgumentException("Limit must be a Finite Number!");
        }
        this.limit = limit;
    }

    @Override
    public boolean interpret(PolicyContext context) {
        return false;
    }

    @Override
    public String describe() {
        return "";
    }

}