package com.example.chris.pcalc.ast;

import android.util.Log;
import com.example.chris.pcalc.numeric.Numeric;

public class AstUnOp<N extends Number> extends AstNode<N> {
    private AstNode<N> right;
    private UnOp op;

    public AstUnOp(UnOp op) {
        this.right = null;
        this.op = op;
        this.depth = 0;
    }

    @Override
    public void setLeft(AstNode<N> left) {
        throw new UnsupportedOperationException("Unary operaters take only right children");
    }

    @Override
    public AstNode<N> getRight() {
        return right;
    }

    @Override
    public void setRight(AstNode<N> right) {
        this.right = right;
    }

    @Override
    public Numeric<N> evaluate() {
        switch (op) {
            case NEGATIVE:
                return right.evaluate().neg();
            case NOT:
                return right.evaluate().not();
            default:
                Log.w("ast/unop", "Unimplemented unary operation " + op);
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public void append(AstNode<N> node) {
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
