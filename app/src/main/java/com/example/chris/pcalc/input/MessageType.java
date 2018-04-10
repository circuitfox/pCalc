package com.example.chris.pcalc.input;

public enum MessageType {
    SYMBOL,
    CLEAR,
    DELETE;

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
        }
        return name;
    }
}
