package com.example.chris.pcalc.ast;

import com.example.chris.pcalc.numeric.Numeric;

public abstract class AstNode<N extends Number> {
    protected int depth;

    public AstNode<N> getLeft() {
        return null;
    }
    public abstract void setLeft(AstNode<N> left);

    public AstNode<N> getRight() {
        return null;
    }
    public abstract void setRight(AstNode<N> right);

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public abstract Numeric<N> evaluate();

    /**
     * Append a node to this tree. When appending,
     * this method tries the left node first, then
     * the right node. If both are full, then it
     * will try appending down the right branch
     * of the tree.
     * @param node The node to append to this tree.
     */
    public void append(AstNode<N> node) {
        if (this.getLeft() == null) {
            this.setLeft(node);
        } else if (this.getRight() == null) {
            this.setRight(node);
        } else {
            this.getRight().append(node);
        }
    }
}
