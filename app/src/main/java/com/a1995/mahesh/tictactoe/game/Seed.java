package com.a1995.mahesh.tictactoe.game;

/**
 * Created by mahesh on 29/7/16.
 */
public class Seed {
    public static final char EMPTY = ' ';
    public static final char NOUGHT = '0';
    public static final char CROSS = 'X';

    private char mSymbol;

    public Seed() {
        mSymbol = EMPTY;
    }

    public Seed(char symbol) {
        mSymbol = symbol;
    }

    public char getSymbol() {
        return mSymbol;
    }
}
