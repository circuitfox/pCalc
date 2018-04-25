package com.example.chris.pcalc.ast;

import com.example.chris.pcalc.numeric.BigInt;
import com.example.chris.pcalc.parse.Tokens;

import java.math.BigInteger;

public class BigIntAst extends Ast<BigInteger> {
    public BigIntAst(Tokens tokens) {
        root = parse(tokens);
    }

    public BigInt evaluate() {
        return (BigInt)this.evaluate(BigInt.ZERO);
    }

    private static AstNode<BigInteger> parse(Tokens tokens) {
        return parse(null, tokens.iterator(), BigInt.ZERO, BigInt.factory());
    }
}
