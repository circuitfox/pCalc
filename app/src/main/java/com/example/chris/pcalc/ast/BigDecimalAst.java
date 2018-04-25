package com.example.chris.pcalc.ast;

import com.example.chris.pcalc.numeric.BigDecimal;
import com.example.chris.pcalc.parse.Tokens;

public class BigDecimalAst extends Ast<java.math.BigDecimal> {
    public BigDecimalAst(Tokens tokens) {
        root = parse(tokens);
    }

    public BigDecimal evaluate() {
        return (BigDecimal)this.evaluate(BigDecimal.ZERO);
    }

    private static AstNode<java.math.BigDecimal> parse(Tokens tokens) {
        return parse(null, tokens.iterator(), BigDecimal.ZERO, BigDecimal.factory());
    }
}
