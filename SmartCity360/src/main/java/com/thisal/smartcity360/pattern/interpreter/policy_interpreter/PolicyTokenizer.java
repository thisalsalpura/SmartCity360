package com.thisal.smartcity360.pattern.interpreter.policy_interpreter;

import com.thisal.smartcity360.enums.TokenType;
import com.thisal.smartcity360.exception.PolicyParseException;
import com.thisal.smartcity360.record.Token;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class PolicyTokenizer {

    private PolicyTokenizer() {
    }

    public static List<Token> tokenize(String source) {
        List<Token> tokens = new ArrayList<>();
        int length = source.length();
        int i = 0;
        while (i < length) {
            char c = source.charAt(i);
            if (Character.isWhitespace(c)) {
                i++;
            } else if (c == '(') {
                tokens.add(new Token(TokenType.LEFT_PAREN, "(", i++));
            } else if (c == ')') {
                tokens.add(new Token(TokenType.RIGHT_PAREN, ")", i++));
            } else if (c == '<' || c == '>' || c == '=' || c == '!') {
                boolean hasEquals = i + 1 < length && source.charAt(i + 1) == '=';
                if (!hasEquals && (c == '=' || c == '!')) {
                    throw new PolicyParseException("Use '==' and '!=' for Comparisons!", i);
                }
                String symbol = hasEquals
                        ? source.substring(i, i + 2)
                        : String.valueOf(c);
                tokens.add(new Token(TokenType.OPERATOR, symbol, i));
                i += symbol.length();
            } else if (Character.isDigit(c)) {
                i = readNumberOrTime(source, i, tokens);
            } else if (Character.isLetter(c) || c == '_') {
                int start = i;
                while (i < length && (Character.isLetterOrDigit(source.charAt(i)) || source.charAt(i) == '_')) {
                    i++;
                }
                tokens.add(new Token(TokenType.IDENTIFIER, source.substring(start, i).toUpperCase(Locale.ROOT), start));
            } else {
                throw new PolicyParseException("Unexpected Character '" + c + "'!", i);
            }
        }
        tokens.add(new Token(TokenType.END, "", length));
        return tokens;
    }

    private static int readNumberOrTime(String source, int start, List<Token> tokens) {
        int length = source.length();
        int i = start;
        while (i < length && Character.isDigit(source.charAt(i))) {
            i++;
        }
        if (i < length && source.charAt(i) == ':') {
            ++i;
            while (i < length && Character.isDigit(source.charAt(i))) {
                i++;
            }
            String text = source.substring(start, i);
            if (!text.matches("\\d{2}:\\d{2}")) {
                throw new PolicyParseException("Time must be written as HH:mm! For example 22:00.", start);
            }
            try {
                LocalTime.parse(text);
            } catch (DateTimeException e) {
                throw new PolicyParseException("'" + text + "' is not a valid Time!", start);
            }
            tokens.add(new Token(TokenType.TIME_LITERAL, text, start));
            return i;
        } else if (i < length && source.charAt(i) == '.') {
            int fractionStart = ++i;
            while (i < length && Character.isDigit(source.charAt(i))) {
                i++;
            }
            if (i == fractionStart) {
                throw new PolicyParseException("Digits expected after the Decimal Point!", start);
            }
        }
        tokens.add(new Token(TokenType.NUMBER, source.substring(start, i), start));
        return i;
    }

}