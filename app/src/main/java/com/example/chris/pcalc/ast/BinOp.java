package com.example.chris.pcalc.ast;

public enum BinOp implements Comparable<BinOp> {
    // this ordering is to enforce PEMDAS
    POW,
    MULTIPLY,
    DIVIDE,
    ADD,
    SUBTRACT;

    public static BinOp fromString(String str) {
        switch (str) {
            case "+":
                return BinOp.ADD;
            case "-":
                return BinOp.SUBTRACT;
            case "*":
                return BinOp.MULTIPLY;
            case "/":
                return BinOp.DIVIDE;
            case "^":
                return BinOp.POW;
            default:
                throw new IllegalArgumentException("Illegal value for binop: " + str);
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case ADD:
                return "+";
            case SUBTRACT:
                return "-";
            case MULTIPLY:
                return "*";
            case DIVIDE:
                return "/";
            case POW:
                return "^";
            default:
                return "";
        }
    }
}
