package com.thisal.smartcity360.pattern.interpreter.policy_interpreter;

import com.thisal.smartcity360.record.PolicyContext;

public interface PolicyExpression {

    boolean interpret(PolicyContext context);

    String describe();

}