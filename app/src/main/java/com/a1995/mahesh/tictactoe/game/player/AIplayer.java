package com.a1995.mahesh.tictactoe.game.player;

import android.graphics.Point;
import android.util.Log;

import com.a1995.mahesh.tictactoe.game.Board;
import com.a1995.mahesh.tictactoe.game.Seed;

import java.util.ArrayList;

/**
 * Created by mahesh on 29/7/16.
 */
public class AIplayer extends Player {
    private static final String TAG = "AI";
    private Point bestMove;

    public AIplayer(Seed seed) {
        mSeed = seed;
    }

    /**
     * returns the best possible move by using minimax algorithm
     * turn = 1 denotes AI's turn and 2 denotes adversary turn
     * depth is used to keep track while backtracking
     */
    private int miniMax(Board board, int turn, int depth) {
        if (board.hasPlayerWon(getSeed())) {
            return 1;
        } else if (board.hasPlayerWon(getAdversarySeed())) {
            return -1;
        } else if (board.getPossibleMoves().isEmpty()) {
            return 0;
        }

        ArrayList<Point> possibleMoves = board.getPossibleMoves();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < possibleMoves.size(); i++) {
            Point point = possibleMoves.get(i);
            // if AI's turn
            if (turn == 1) {
                board.setMove(point, getSeed());
                int currentScore = miniMax(board, 2, depth + 1);
                max = Math.max(currentScore, max);

                if (currentScore >= 0) {
                    if (depth == 0) {
                        bestMove = point;
                    }
                }
                // if you are winning, don't recurse further
                if (currentScore == 1) {
                    //clear the move and break the loop
                    board.setMove(point, new Seed(Seed.EMPTY));
                    break;
                }
                // last move when game draws
                if (i == possibleMoves.size() - 1 && max < 0 && depth == 0) {
                    bestMove = point;
                }
            } else if (turn == 2) {            //adversary's turn, adversary should try to minimize the score
                board.setMove(point, getAdversarySeed());
                int currentScore = miniMax(board, 1, depth + 1);
                min = Math.min(currentScore, min);
                // if adversary wins do not recurse further
                if (min == -1) {
                    board.setMove(point, new Seed(Seed.EMPTY));
                    break;
                }
            }
            // reset the point
            board.setMove(point, new Seed(Seed.EMPTY));
        }

        return turn == 1 ? max : min;

    }

    /**
     * This method returns a the best move
     */
    public Point getBestMove(Board board) {
        miniMax(board, 1, 0);
        Log.i(TAG, "best move:" + bestMove.x + " " + bestMove.y);
        return bestMove;
    }

}
