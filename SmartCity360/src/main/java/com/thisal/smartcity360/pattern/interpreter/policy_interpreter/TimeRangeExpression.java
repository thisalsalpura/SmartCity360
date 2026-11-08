package com.thisal.smartcity360.pattern.interpreter.policy_interpreter;

import com.thisal.smartcity360.record.PolicyContext;

import java.time.LocalTime;
import java.util.Objects;

public class TimeRangeExpression implements PolicyExpression {

    private final LocalTime start;

    private final LocalTime end;

    public TimeRangeExpression(LocalTime start, LocalTime end) {
        this.start = Objects.requireNonNull(start, "Start Time must not be Null!");
        this.end = Objects.requireNonNull(end, "End Time must not be Null!");
        if (start.equals(end)) {
            throw new IllegalArgumentException("Start and End Time must be Different!");
        }
    }

    @Override
    public boolean interpret(PolicyContext context) {
        LocalTime now = context.time();
        if (start.isBefore(end)) {
            return !now.isBefore(start) && now.isBefore(end);
        } else {
            return !now.isBefore(start) || now.isBefore(end);
        }
    }

    @Override
    public String describe() {
        return "Time is between " + start + " - " + end;
    }

}