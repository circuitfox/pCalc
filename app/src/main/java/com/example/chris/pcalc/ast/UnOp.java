package com.example.chris.pcalc.ast;

public enum UnOp {
    NEGATIVE,
    NOT;

    public static UnOp fromString(String str) {
        switch (str) {
            case "-":
                return NEGATIVE;
            case "not":
                return NOT;
            default:
                throw new IllegalArgumentException("Illegal value for unop: " + str);
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case NEGATIVE:
                return "-";
            case NOT:
                return "not ";
            default:
                return "";
        }
    }
}
