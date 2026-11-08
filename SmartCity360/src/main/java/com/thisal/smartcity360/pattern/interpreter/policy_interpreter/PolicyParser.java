package com.thisal.smartcity360.pattern.interpreter.policy_interpreter;

import com.thisal.smartcity360.enums.TokenType;
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
        PolicyExpression expression = parser.parseOr();
        Token extra = parser.peek();
        if (extra.type() != TokenType.END) {
            throw new PolicyParseException("Unexpected '" + extra.text() + "' after end of the Rule!", extra.position());
        }
        return expression;
    }

    private static String describe(Token token) {
        return token.type() == TokenType.END
                ? "The End of the Rule!"
                : token.text();
    }

    private PolicyExpression parseOr() {
        PolicyExpression left = parseAnd();
        while (matchKeyword("OR")) {
            left = new OrExpression(left, parseAnd());
        }
        return left;
    }

    private PolicyExpression parseAnd() {
        PolicyExpression left = parseNot();
        while (matchKeyword("AND")) {
            left = new AndExpression(left, parseNot());
        }
        return left;
    }

    private PolicyExpression parseNot() {
        if (matchKeyword("NOT")) {
            enter();
            try {
                return new NotExpression(parseNot());
            } finally {
                --depth;
            }
        }
    }

    private PolicyExpression parsePrimary() {

    }

    private boolean matchKeyword(String keyword) {
        Token token = peek();
        if (token.type() == TokenType.IDENTIFIER && keyword.equals(token.text())) {
            index++;
            return true;
        }
        return false;
    }

    private Token expect(TokenType type, String what) {
        Token token = peek();
        if (token.type() != type) {
            throw new PolicyParseException("Expected " + what + "! But found " + describe(token) + ".", token.position());
        }
        index++;
        return token;
    }

    private Token peek() {
        return tokens.get(index);
    }

    private void enter() {
        if (++depth > PolicyParser.MAX_DEPTH) {
            throw new PolicyParseException("Rule is nexted too Deeply! Limit is " + PolicyParser.MAX_DEPTH + ".", peek().position());
        }
    }

}