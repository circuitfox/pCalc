package com.example.chris.pcalc.numeric;

public interface NumericFactory<N extends Number, T extends Numeric<N>> {
    T fromString(String str);
}
