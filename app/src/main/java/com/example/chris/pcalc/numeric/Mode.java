package com.example.chris.pcalc.numeric;

public enum Mode {
    INT,
    REAL;

    public static Mode fromString(String mode) {
        switch (mode) {
            case "int":
                return INT;
            case "real":
                return REAL;
            default:
                throw new IllegalArgumentException("Unknown mode value " + mode);
        }
    }
}
