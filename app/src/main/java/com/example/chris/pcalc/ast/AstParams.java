package com.example.chris.pcalc.ast;

import com.example.chris.pcalc.numeric.Numeric;

import java.util.ArrayList;

public class AstParams<N extends Number> extends AstNode<N> {
    private ArrayList<AstNode<N>> params;

    public AstParams(AstNode<N> param) {
        params = new ArrayList<>();
        params.add(param);
        depth = 0;
    }

    @Override
    public void setLeft(AstNode<N> left) {
        throw new UnsupportedOperationException("Params have no children");
    }

    @Override
    public void setRight(AstNode<N> right) {
        throw new UnsupportedOperationException("Params have no children");
    }

    @Override
    public void append(AstNode<N> node) {
        params.add(node);
    }

    public ArrayList<AstNode<N>> getParams() {
        return params;
    }

    public ArrayList<Numeric<N>> getEvaluatedParams() {
        ArrayList<Numeric<N>> evaluated = new ArrayList<>();
        for (AstNode<N> node: params) {
            evaluated.add(node.evaluate());
        }
        return evaluated;
    }

    @Override
    public int getDepth() {
        return 0;
    }

    @Override
    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public Numeric<N> evaluate() {
        throw new UnsupportedOperationException("Params cannot be evaluated without a function");
    }

    @Override
    public String toString() {
        if (params.size() >= 1) {
            StringBuilder sb = new StringBuilder(params.get(0).toString());
            for (int i = 1; i < params.size(); i++) {
                sb.append(", ").append(params.get(i).toString());
            }
            return sb.toString();
        } else {
            return "";
        }
    }
}
