package com.a1995.mahesh.tictactoe.game.player;

import android.graphics.Point;

import com.a1995.mahesh.tictactoe.game.Board;
import com.a1995.mahesh.tictactoe.game.Seed;

/**
 * Created by mahesh on 29/7/16.
 */
public abstract class Player {
    protected Seed mSeed;

    protected void playMove(Board board, Point point, Seed seed) {
        board.setMove(point, seed);
    }

    public Seed getSeed() {
        return mSeed;
    }

    public Seed getAdversarySeed() {
        if (mSeed.getSymbol() == Seed.CROSS) {
            return new Seed(Seed.NOUGHT);
        } else {
            return new Seed(Seed.CROSS);
        }
    }
}
