package com.example.chris.pcalc.ast;

public enum UnOp {
    NEGATIVE;

    public static UnOp fromString(String str) {
        switch (str) {
            case "-":
                return NEGATIVE;
            default:
                throw new IllegalArgumentException("Illegal value for unop: " + str);
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case NEGATIVE:
                return "-";
            default:
                return "";
        }
    }
}
