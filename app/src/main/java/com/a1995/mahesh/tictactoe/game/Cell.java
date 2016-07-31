package com.a1995.mahesh.tictactoe.game;

/**
 * Created by mahesh on 29/7/16.
 */
public class Cell {
    private Seed mSeed;

    public Cell() {
        mSeed = new Seed();
    }

    public Seed getSeed() {
        return mSeed;
    }

    public void setSeed(Seed seed) {
        mSeed = seed;
    }
}
