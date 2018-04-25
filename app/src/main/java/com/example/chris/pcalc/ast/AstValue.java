package com.example.chris.pcalc.ast;

import com.example.chris.pcalc.numeric.Numeric;

public class AstValue<N extends Number> extends AstNode<N> {
    private Numeric<N> value;
    private int depth;

    public AstValue(Numeric<N> value) {
        this.value = value;
        this.depth = 0;
    }

    @Override
    public void setLeft(AstNode<N> left) {
        throw new UnsupportedOperationException("AstValue has no children");
    }

    @Override
    public void setRight(AstNode<N> right) {
        throw new UnsupportedOperationException("AstValue has no children");
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public Numeric<N> evaluate() {
        return value;
    }

    @Override
    public void append(AstNode<N> node) {
        throw new UnsupportedOperationException("AstValue cannot be appended to");
    }

    public String toString() {
        return value.toString();
    }
}
