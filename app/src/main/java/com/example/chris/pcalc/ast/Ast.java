package com.example.chris.pcalc.ast;

import android.util.Log;
import com.example.chris.pcalc.numeric.Numeric;
import com.example.chris.pcalc.numeric.NumericFactory;
import com.example.chris.pcalc.parse.Token;

import java.util.Iterator;

public abstract class Ast<N extends Number> {
    protected AstNode<N> root;

    public AstNode<N> getRoot() {
        return root;
    }

    protected Numeric<N> evaluate(Numeric<N> def) {
        try {
            if (root != null) {
                return root.evaluate();
            }
        } catch (Exception ex) {
            Log.wtf("pCalc", "Exception while evaluating", ex);
        }
        return def;
    }


    public String toString() {
        if (root != null) {
            return root.toString();
        } else {
            return "";
        }
    }

    public static <N extends Number, T extends Numeric<N>>
    AstNode<N> parse(AstNode<N> root, Iterator<Token> tokens, T zero, NumericFactory<N, T> factory) {
        AstNode<N> node = root;
        int depth = 0;
        if (node != null) {
            depth = node.getDepth();
        }
        while (tokens.hasNext()) {
            Token token = tokens.next();
            switch (token.getType()) {
                case TOKEN_GROUP:
                    if (token.getValue().equals("(")) {
                        Log.i("ast/parse", "parse into group");
                        depth++;
                        if (node == null) {
                            node = parse(null, tokens, zero, factory);
                        } else {
                            node.setRight(parse(node.getRight(), tokens, zero, factory));
                        }
                    } else if (token.getValue().equals(")")) {
                        Log.i("ast/parse", "return from group");
                        // we'll always take groups recursively
                        return node;
                    } else {
                        Log.w("ast/parse", "Unknown group value '" + token.getValue() + "'");
                    }
                    break;
                case TOKEN_NUMBER:
                    T value = zero;
                    try {
                        value = factory.fromString(token.getValue());
                    } catch (NumberFormatException ex) {
                        Log.e("ast/parse", "Could not parse number token");
                        Log.wtf("ast/parse", ex);
                    }

                    if (node == null) {
                        node = new AstValue<>(value);
                        node.setDepth(depth);
                    } else {
                        AstNode child = new AstValue<>(value);
                        child.setDepth(depth);
                        node.append(new AstValue<>(value));
                    }
                    break;
                case TOKEN_OPERATOR:
                    if (node == null) {
                        node = new AstUnOp<>(UnOp.fromString(token.getValue()));
                        node.setDepth(depth);
                    }  else if (node instanceof AstValue || node instanceof AstUnOp) {
                        AstNode<N> left = node;
                        node = new AstBinOp<>(BinOp.fromString(token.getValue()), left);
                        node.setDepth(depth);
                    } else if (node instanceof AstBinOp) {
                        BinOp lastOp = ((AstBinOp)node).getOp();
                        BinOp op = BinOp.fromString(token.getValue());
                        Log.d("ast/parse", "lastOp = '" + lastOp + "' op = '" + op + "'");
                        Log.d("ast/parse",
                                "node:depth = " + node.getDepth() + " depth = " + depth);
                        if (node.getDepth() < depth || op.compareTo(lastOp) > 0) {
                            // we are higher in precedence. The current tree becomes
                            // our left child
                            node = new AstBinOp<>(op, node);
                            node.setDepth(depth);
                        } else {
                            // we are lower than or equal to in precedence. We become
                            // the right child of the current tree, with its current
                            // right child as our left
                            AstNode<N> child = new AstBinOp<>(op, node.getRight());
                            node.setRight(parse(child, tokens, zero, factory));
                        }
                    } else {
                        Log.w("ast/parse", "unimplemented ast node type");
                    }
                    break;
            }
        }
        return node;
    }
}
