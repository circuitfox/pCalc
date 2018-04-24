package com.example.chris.pcalc.ast;

public enum BinOp implements Comparable<BinOp> {
    // this ordering is to enforce PEMDAS
    POW,
    MULTIPLY,
    DIVIDE,
    MOD,
    ADD,
    SUBTRACT,
    SHL,
    SHR,
    AND,
    XOR,
    OR;

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
            case "and":
                return BinOp.AND;
            case "or":
                return BinOp.OR;
            case "xor":
                return BinOp.XOR;
            case "%":
                return BinOp.MOD;
            case "<<":
                return BinOp.SHL;
            case ">>":
                return BinOp.SHR;
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
            case AND:
                return "and";
            case OR:
                return "or";
            case XOR:
                return "xor";
            case MOD:
                return "%";
            case SHL:
                return "<<";
            case SHR:
                return ">>";
            default:
                return "";
        }
    }
}
