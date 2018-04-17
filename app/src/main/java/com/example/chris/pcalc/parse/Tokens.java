package com.example.chris.pcalc.parse;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;

public class Tokens implements Iterable<Token> {
    private ArrayList<Token> tokens;

    public Tokens() {
        tokens = new ArrayList<>();
    }

    public void add(Token token) {
        tokens.add(token);
    }

    @NonNull
    public Iterator<Token> iterator() {
        return tokens.iterator();
    }

    @Override
    public String toString() {
        return tokens.toString();
    }
}
