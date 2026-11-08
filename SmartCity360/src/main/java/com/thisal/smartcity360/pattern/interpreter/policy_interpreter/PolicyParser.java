package com.thisal.smartcity360.pattern.interpreter.policy_interpreter;

import com.thisal.smartcity360.exception.PolicyParseException;
import com.thisal.smartcity360.record.Token;

import java.util.List;

public final class PolicyParser {

    private static final int MAX_RULE_LENGTH = 500;

    private static final int MAX_DEPTH = 20;

    private final List<Token> tokens;

    private int index;

    private int depth;

    public PolicyParser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public static PolicyExpression parse(String rule) {
        if (rule == null || rule.isBlank()) {
            throw new PolicyParseException("Rule must not be Blank!", 0);
        } else if (rule.length() > PolicyParser.MAX_RULE_LENGTH) {
            throw new PolicyParseException("Rule must be at most " + PolicyParser.MAX_RULE_LENGTH + " Characters!", 0);
        }
        PolicyParser parser = new PolicyParser(PolicyTokenizer.tokenize(rule));
    }

}