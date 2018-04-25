package com.example.chris.pcalc.input;

public enum MessageType {
    SYMBOL,
    CLEAR,
    DELETE,
    EQUALS,
    MODE;

    @Override
    public String toString() {
        String name = "";
        switch (this) {
            case SYMBOL:
                name = "SYMBOL";
                break;
            case CLEAR:
                name = "CLEAR";
                break;
            case DELETE:
                name = "DELETE";
                break;
            case EQUALS:
                name = "EQUALS";
                break;
            case MODE:
                name = "MODE";
                break;
        }
        return name;
    }
}
