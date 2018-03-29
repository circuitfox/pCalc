package com.example.chris.pcalc.input;

public enum MessageType {
    SYMBOL,
    CLEAR;

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
        }
        return name;
    }
}
