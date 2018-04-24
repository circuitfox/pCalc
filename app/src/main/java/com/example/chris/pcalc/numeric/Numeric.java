package com.example.chris.pcalc.numeric;

public interface Numeric<N extends Number> {

    Numeric<N> add(Numeric<N> rhs);
    Numeric<N> sub(Numeric<N> rhs);
    Numeric<N> mul(Numeric<N> rhs);
    Numeric<N> div(Numeric<N> rhs);
    Numeric<N> pow(Numeric<N> rhs);
    Numeric<N> mod(Numeric<N> rhs);
    Numeric<N> and(Numeric<N> rhs);
    Numeric<N> or(Numeric<N> rhs);
    Numeric<N> xor(Numeric<N> rhs);
    Numeric<N> shl(Numeric<N> rhs);
    Numeric<N> shr(Numeric<N> rhs);

    Numeric<N> neg();
    Numeric<N> not();

    N getSelf();

    byte byteValue();
    short shortValue();
    int intValue();
    long longValue();
    float floatValue();
    double doubleValue();
}
