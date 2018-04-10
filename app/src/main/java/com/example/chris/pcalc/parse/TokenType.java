package com.example.chris.pcalc.parse;

public enum TokenType {
    TOKEN_NUMBER,
    TOKEN_GROUP,
    TOKEN_OPERATOR;

    @Override
    public String toString() {
        String type = "";
        switch (this) {
            case TOKEN_NUMBER:
                type = "number";
                break;
            case TOKEN_GROUP:
                type = "group";
                break;
            case TOKEN_OPERATOR:
                type = "operator";
                break;
        }
        return type;
    }
}
