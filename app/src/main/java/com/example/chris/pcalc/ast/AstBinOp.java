package com.example.chris.pcalc.ast;

import android.util.Log;

public class AstBinOp extends AstNode {

    private AstNode left;
    private AstNode right;
    private BinOp op;
    private int depth;

    public AstBinOp(BinOp op, AstNode left) {
        this.op = op;
        this.left = left;
        this.right = null;
        this.depth = 0;
    }

    @Override
    public void setLeft(AstNode left) {
        this.left = left;
    }

    @Override
    public void setRight(AstNode right) {
        this.right = right;
    }

    @Override
    public AstNode getLeft() {
        return left;
    }

    @Override
    public AstNode getRight() {
        return right;
    }

    public BinOp getOp() {
        return op;
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
            case ADD:
                return left.evaluate() + right.evaluate();
            case SUBTRACT:
                return left.evaluate() - right.evaluate();
            case MULTIPLY:
                return left.evaluate() * right.evaluate();
            case DIVIDE:
                return left.evaluate() / right.evaluate();
            default:
                Log.w("ast/binop", "Unimplemented binary operation " + op);
                return 0;
        }
    }

    @Override
    public String toString() {
        return "("  + left + " " + op + " " + right + ")";
    }
}
