package com.thisal.smartcity360.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public enum ComparisonOperator {

    LT("<"),
    LE("<="),
    GT(">"),
    GE(">="),
    EQ("=="),
    NE("!=");

    private static final double EPSILON = 1e-9;

    private final String symbol;

    public static Optional<ComparisonOperator> fromSymbol(String symbol) {
        for (ComparisonOperator operator : values()) {
            if (operator.symbol.equals(symbol)) {
                return Optional.of(operator);
            }
        }
        return Optional.empty();
    }

    public boolean test(double actual, double limit) {
        return switch (this) {
            case LT -> actual < limit;
            case LE -> actual <= limit;
            case GT -> actual > limit;
            case GE -> actual >= limit;
            case EQ -> Math.abs(actual - limit) < ComparisonOperator.EPSILON;
            case NE -> Math.abs(actual - limit) >= ComparisonOperator.EPSILON;
        };
    }

}