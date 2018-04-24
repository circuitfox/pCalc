package com.example.chris.pcalc.parse;

import android.util.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Tokenizer {
    private static final Set<Character> GROUP_CHARS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList('(', ')')
    ));
    private static final Set<Character> OP_CHARS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList('+', '-', '*', '/', '^', '%')
    ));
    private static final Set<String> BITWISE_OPS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("and", "or", "not", "xor", ">>", "<<")
    ));

    private char[] stream;

    public Tokenizer(String stream) {
        this.stream = stream.toCharArray();
    }

    public Tokens tokenize() {
        Tokens tokens = new Tokens();
        StringBuilder currentToken = null;
        int tokenPosition = -1;
        Log.d("parse", "stream: " + String.valueOf(stream));
        Log.d("parse", "stream.length: " + String.valueOf(stream.length));
        for (int i = 0; i < stream.length; i++) {
            char c = stream[i];

            Log.v("parse", "c: " + c);
            Log.v("parse", "i: " + i);
            if (Character.isDigit(c)) {
                Log.d("parse", "isDigit: " + c);
                if (tokenPosition == -1) {
                    tokenPosition = i;
                }
                if (currentToken == null) {
                    currentToken = new StringBuilder();
                }
                currentToken.append(c);
            } else {
                if (currentToken != null) {
                    Token token = new Token(
                            TokenType.TOKEN_NUMBER,
                            tokenPosition,
                            currentToken.toString()
                    );
                    tokens.add(token);
                    tokenPosition = -1;
                    currentToken = null;
                }
            }

            if (GROUP_CHARS.contains(c)) {
                Token token = new Token(TokenType.TOKEN_GROUP, i, String.valueOf(c));
                tokens.add(token);
            } else if (OP_CHARS.contains(c)) {
                Token token = new Token(TokenType.TOKEN_OPERATOR, i, String.valueOf(c));
                tokens.add(token);
            } else {
                // lookahead
                if (Character.isLetter(c)) {
                    if (i < stream.length - 2) {
                        String bitwise = String.valueOf(stream, i, 3).trim();
                        if (BITWISE_OPS.contains(bitwise)) {
                            tokens.add(new Token(TokenType.TOKEN_OPERATOR, i, bitwise));
                        }
                        i += 2;
                    } else if (i < stream.length - 1) {
                        String or = String.valueOf(stream, i, 2);
                        if (or.equals("or")) {
                            tokens.add(new Token(TokenType.TOKEN_OPERATOR, i, or));
                        }
                        i += 1;
                    }
                } else if (c == '<' || c == '>' && i < stream.length - 1) {
                    String shift = String.valueOf(stream, i, 2);
                    if (BITWISE_OPS.contains(shift)) {
                        tokens.add(new Token(TokenType.TOKEN_OPERATOR, i, shift));
                    }
                }
            }
        }

        // number could be at the end of the array and doesn't get added by the for
        if (currentToken != null) {
            Token token = new Token(TokenType.TOKEN_NUMBER, tokenPosition, currentToken.toString());
            tokens.add(token);
        }
        return tokens;
    }
}
