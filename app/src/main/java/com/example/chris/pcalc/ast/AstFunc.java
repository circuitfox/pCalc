package com.example.chris.pcalc.ast;

import com.example.chris.pcalc.numeric.Func;
import com.example.chris.pcalc.numeric.Numeric;

import java.util.ArrayList;

public class AstFunc<N extends Number> extends AstNode<N> {
    private String name;
    private AstParams<N> params;
    private Func<N> function;

    public AstFunc(String name, Func<N> function) {
        this.name = name;
        this.function = function;
        this.depth = 0;
    }

    @Override
    public void setLeft(AstNode<N> left) {
        throw new UnsupportedOperationException("Function parameters are taken on the right");
    }

    @Override
    public void setRight(AstNode<N> right) {
        if (right instanceof AstParams) {
            this.params = (AstParams<N>)right;
        } else {
            throw new IllegalArgumentException("Functions can only take parameters");
        }
    }

    @Override
    public Numeric<N> evaluate() {
        ArrayList<Numeric<N>> evaluated = params.getEvaluatedParams();
        return function.apply(evaluated);
    }

    @Override
    public String toString() {
        return name + " (" + params + ")";
    }
}
