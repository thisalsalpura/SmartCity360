package com.thisal.smartcity360.pattern.interpreter.policy_interpreter;

import com.thisal.smartcity360.record.PolicyContext;

import java.util.Objects;

public class NotExpression implements PolicyExpression {

    private final PolicyExpression operand;

    public NotExpression(PolicyExpression operand) {
        this.operand = Objects.requireNonNull(operand, "Operand must not be Null!");
    }

    @Override
    public boolean interpret(PolicyContext context) {
        return !operand.interpret(context);
    }

    @Override
    public String describe() {
        return "NOT " + operand.describe();
    }

}