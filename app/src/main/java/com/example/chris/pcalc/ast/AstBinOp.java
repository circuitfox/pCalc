package com.example.chris.pcalc.ast;

import android.util.Log;
import com.example.chris.pcalc.numeric.Numeric;

public class AstBinOp<N extends Number> extends AstNode<N> {

    private AstNode<N> left;
    private AstNode<N> right;
    private BinOp op;
    private int depth;

    public AstBinOp(BinOp op, AstNode<N> left) {
        this.op = op;
        this.left = left;
        this.right = null;
        this.depth = 0;
    }

    @Override
    public void setLeft(AstNode<N> left) {
        this.left = left;
    }

    @Override
    public void setRight(AstNode<N> right) {
        this.right = right;
    }

    @Override
    public AstNode<N> getLeft() {
        return left;
    }

    @Override
    public AstNode<N> getRight() {
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
    public Numeric<N> evaluate() {
        switch (op) {
            case ADD:
                return left.evaluate().add(right.evaluate());
            case SUBTRACT:
                return left.evaluate().sub(right.evaluate());
            case MULTIPLY:
                return left.evaluate().mul(right.evaluate());
            case DIVIDE:
                return left.evaluate().div(right.evaluate());
            case POW:
                return left.evaluate().pow(right.evaluate());
            case AND:
                return left.evaluate().and(right.evaluate());
            case OR:
                return left.evaluate().or(right.evaluate());
            case XOR:
                return left.evaluate().xor(right.evaluate());
            case MOD:
                return left.evaluate().mod(right.evaluate());
            case SHL:
                return left.evaluate().shl(right.evaluate());
            case SHR:
                return left.evaluate().shr(right.evaluate());
            default:
                Log.w("ast/binop", "Unimplemented binary operation " + op);
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public String toString() {
        return "("  + left + " " + op + " " + right + ")";
    }
}
