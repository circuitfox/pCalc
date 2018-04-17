package com.example.chris.pcalc.ast;

public class AstValue extends AstNode {
    private int value;
    private int depth;

    public AstValue(int value) {
        this.value = value;
        this.depth = 0;
    }

    @Override
    public void setLeft(AstNode left) {
        throw new UnsupportedOperationException("AstValue has no children");
    }

    @Override
    public void setRight(AstNode right) {
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
    public int evaluate() {
        return value;
    }

    @Override
    public void append(AstNode node) {
        throw new UnsupportedOperationException("AstValue cannot be appended to");
    }

    public String toString() {
        return Integer.toString(value);
    }
}
