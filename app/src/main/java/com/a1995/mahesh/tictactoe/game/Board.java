package com.a1995.mahesh.tictactoe.game;

import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by mahesh on 29/7/16.
 */
public class Board {
    private static final String TAG = "Board";
    private Cell[][] cells;

    public Board() {
        cells = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    /**
     * this method lays the seed in a cell pointed by point
     * it returns true if it's a valid move, otherwise false
     */
    public boolean setMove(Point point, Seed seed) {
        int x = point.x;
        int y = point.y;

        if (cells[x][y].getSeed().getSymbol() == Seed.EMPTY) {
            cells[x][y].setSeed(seed);
            return true;
        } else if (seed.getSymbol() == Seed.EMPTY) {
            cells[x][y].setSeed(seed);
            return true;
        } else {
            Log.i(TAG, "value at position is:" + x + " " + y);
            return false;
        }
    }

    public boolean isGameOver() {
        return (hasPlayerWon(new Seed(Seed.CROSS)) || hasPlayerWon(new Seed(Seed.NOUGHT)) || getPossibleMoves().isEmpty());
    }

    /**
     * returns a seed at the coordinates pointed by the point
     */
    public Seed getSeedFromPosition(Point point) {
        return cells[point.x][point.y].getSeed();
    }

    /**
     * this function returns a list of cordinates of empty cells
     */
    public ArrayList<Point> getPossibleMoves() {
        ArrayList<Point> possibleMoves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // if the position is empty add to the list
                if (cells[i][j].getSeed().getSymbol() == Seed.EMPTY) {
                    possibleMoves.add(new Point(i, j));
                }
            }
        }

        return possibleMoves;
    }

    /**
     * This method checks whether a the player with a given seed has won
     * it is called after each move
     */
    public boolean hasPlayerWon(Seed playerSeed) {
        char playerSeedSymbol = playerSeed.getSymbol();
        // check diognal
        if ((cells[0][0].getSeed().getSymbol() == playerSeedSymbol && cells[1][1].getSeed().getSymbol() == playerSeedSymbol
                && cells[2][2].getSeed().getSymbol() == playerSeedSymbol) || (cells[0][2].getSeed().getSymbol() == playerSeedSymbol
                && cells[1][1].getSeed().getSymbol() == playerSeedSymbol && cells[2][0].getSeed().getSymbol() == playerSeedSymbol)) {
            return true;
        }

        // check rows
        for (int i = 0; i < 3; i++) {
            int count = 0;
            for (int j = 0; j < 3; j++) {
                if (cells[i][j].getSeed().getSymbol() == playerSeedSymbol) {
                    count++;
                }
            }
            if (count == 3) {
                return true;
            }
        }

        //check Columns
        for (int i = 0; i < 3; i++) {
            int count = 0;
            for (int j = 0; j < 3; j++) {
                if (cells[j][i].getSeed().getSymbol() == playerSeedSymbol) {
                    count++;
                }
            }
            if (count == 3) {
                return true;
            }
        }

        return false;
    }
}
