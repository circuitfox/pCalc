package com.example.chris.pcalc.numeric;

import java.util.List;

public interface Func<N extends Number> {
    Numeric<N> apply(List<Numeric<N>> params);
}
