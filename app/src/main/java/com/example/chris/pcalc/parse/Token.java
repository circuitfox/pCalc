package com.example.chris.pcalc.parse;

public class Token {
    /**
     * The type of this token.
     */
    private TokenType type;

    /**
     * The index this token starts at.
     */
    private int position;

    /**
     * The value of this token.
     */
    private String value;

    public Token(TokenType type, int position, String value) {
        this.type = type;
        this.position = position;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public int getPosition() {
        return position;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return type.toString() + "('" + value + "')@" + position;
    }
}
