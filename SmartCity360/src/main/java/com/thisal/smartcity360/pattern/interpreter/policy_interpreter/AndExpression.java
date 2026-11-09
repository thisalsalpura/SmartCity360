package com.thisal.smartcity360.pattern.interpreter.policy_interpreter;

import com.thisal.smartcity360.record.PolicyContext;

import java.util.Objects;

public class AndExpression implements PolicyExpression {

    private final PolicyExpression left;

    private final PolicyExpression right;

    public AndExpression(PolicyExpression left, PolicyExpression right) {
        this.left = Objects.requireNonNull(left, "Left Expression must not be Null!");
        this.right = Objects.requireNonNull(right, "Right Expression must not be Null!");
    }

    @Override
    public boolean interpret(PolicyContext context) {
        return left.interpret(context) && right.interpret(context);
    }

    @Override
    public String describe() {
        return left.describe() + " AND " + right.describe();
    }

}