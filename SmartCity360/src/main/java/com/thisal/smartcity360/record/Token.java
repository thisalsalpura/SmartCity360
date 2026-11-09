package com.thisal.smartcity360.record;

import com.thisal.smartcity360.enums.TokenType;

public record Token(
        TokenType type,
        String text,
        int position
) {
}