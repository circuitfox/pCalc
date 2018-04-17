package com.example.chris.pcalc.ast;

public abstract class AstNode {
    public AstNode getLeft() {
        return null;
    }
    public abstract void setLeft(AstNode left);

    public AstNode getRight() {
        return null;
    }
    public abstract void setRight(AstNode right);

    public abstract int getDepth();
    public abstract void setDepth(int depth);

    public abstract int evaluate();

    /**
     * Append a node to this tree. When appending,
     * this method tries the left node first, then
     * the right node. If both are full, then it
     * will try appending down the right branch
     * of the tree.
     * @param node The node to append to this tree.
     */
    public void append(AstNode node) {
        if (this.getLeft() == null) {
            this.setLeft(node);
        } else if (this.getRight() == null) {
            this.setRight(node);
        } else {
            this.getRight().append(node);
        }
    }
}
