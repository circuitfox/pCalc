package com.example.chris.pcalc.ast;

import android.util.Log;

public class AstUnOp extends AstNode {

    private AstNode right;
    private UnOp op;
    private int depth;

    public AstUnOp(UnOp op) {
        this.right = null;
        this.op = op;
        this.depth = 0;
    }

    @Override
    public void setLeft(AstNode left) {
        throw new UnsupportedOperationException("Unary operaters take only right children");
    }

    @Override
    public AstNode getRight() {
        return right;
    }

    @Override
    public void setRight(AstNode right) {
        this.right = right;
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
        switch (op) {
            case NEGATIVE:
                return -right.evaluate();
            case NOT:
                return ~right.evaluate();
            default:
                Log.w("ast/unop", "Unimplemented unary operation " + op);
                return 0;
        }
    }

    @Override
    public void append(AstNode node) {
        if (right == null) {
            right = node;
        } else {
            right.append(node);
        }
    }
    public String toString() {
        return op + "(" + right + ")";
    }
}
