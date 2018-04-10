package com.example.chris.pcalc.parse;

import java.util.ArrayList;
import java.util.Iterator;

public class Tokens {
    private ArrayList<Token> tokens;

    public Tokens() {
        tokens = new ArrayList<>();
    }

    public void add(Token token) {
        tokens.add(token);
    }

    public Iterator<Token> iterator() {
        return tokens.iterator();
    }

    @Override
    public String toString() {
        return tokens.toString();
    }
}
