package com.a1995.mahesh.tictactoe;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a1995.mahesh.tictactoe.game.Board;
import com.a1995.mahesh.tictactoe.game.Seed;
import com.a1995.mahesh.tictactoe.game.player.AIplayer;
import com.a1995.mahesh.tictactoe.game.player.HumanPlayer;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GAMEACTIVITY";
    private Button mCrossButton;
    private Button mNoughtButton;
    private Button mResetButton;

    private TableLayout mTableLayout;
    private TextView mCell1;
    private TextView mCell2;
    private TextView mCell3;
    private TextView mCell4;
    private TextView mCell5;
    private TextView mCell6;
    private TextView mCell7;
    private TextView mCell8;
    private TextView mCell9;
    private Board mBoard;
    private HumanPlayer humanPlayer;
    private AIplayer aiplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTableLayout = (TableLayout) findViewById(R.id.table_layout);
        enableDisableView(mTableLayout, false);
        inflateButtons();
        inflateCells();
    }

    /**
     * This method enables or disables the view
     * on creation of activity, all the cells are disabled until the symbol is selected
     */

    public void enableDisableView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;

            for (int idx = 0; idx < group.getChildCount(); idx++) {
                enableDisableView(group.getChildAt(idx), enabled);
            }
        }
    }

    private void inflateCells() {
        mCell1 = (TextView) findViewById(R.id.cell_0_0);
        mCell2 = (TextView) findViewById(R.id.cell_0_1);
        mCell3 = (TextView) findViewById(R.id.cell_0_2);
        mCell4 = (TextView) findViewById(R.id.cell_1_0);
        mCell5 = (TextView) findViewById(R.id.cell_1_1);
        mCell6 = (TextView) findViewById(R.id.cell_1_2);
        mCell7 = (TextView) findViewById(R.id.cell_2_0);
        mCell8 = (TextView) findViewById(R.id.cell_2_1);
        mCell9 = (TextView) findViewById(R.id.cell_2_2);

        mCell1.setOnClickListener(this);
        mCell2.setOnClickListener(this);
        mCell3.setOnClickListener(this);
        mCell4.setOnClickListener(this);
        mCell5.setOnClickListener(this);
        mCell6.setOnClickListener(this);
        mCell7.setOnClickListener(this);
        mCell8.setOnClickListener(this);
        mCell9.setOnClickListener(this);
    }

    private void inflateButtons() {
        mCrossButton = (Button) findViewById(R.id.cross_button);
        mCrossButton.setOnClickListener(this);

        mNoughtButton = (Button) findViewById(R.id.nought_button);
        mNoughtButton.setOnClickListener(this);

        mResetButton = (Button) findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cross_button:
                setUpGame(Seed.CROSS);
                mCrossButton.setEnabled(false);
                mNoughtButton.setEnabled(false);
                enableDisableView(mTableLayout, true);
                break;

            case R.id.nought_button:
                setUpGame(Seed.NOUGHT);
                mCrossButton.setEnabled(false);
                mNoughtButton.setEnabled(false);
                enableDisableView(mTableLayout, true);
                break;

            case R.id.reset_button:
                reset();
                break;

            case R.id.cell_0_0:
                playHumanMove(new Point(0, 0), R.id.cell_0_0);
                break;

            case R.id.cell_0_1:
                playHumanMove(new Point(0, 1), R.id.cell_0_1);
                break;

            case R.id.cell_0_2:
                playHumanMove(new Point(0, 2), R.id.cell_0_2);
                break;

            case R.id.cell_1_0:
                playHumanMove(new Point(1, 0), R.id.cell_1_0);
                break;

            case R.id.cell_1_1:
                playHumanMove(new Point(1, 1), R.id.cell_1_1);
                break;

            case R.id.cell_1_2:
                playHumanMove(new Point(1, 2), R.id.cell_1_2);
                break;

            case R.id.cell_2_0:
                playHumanMove(new Point(2, 0), R.id.cell_2_0);
                break;

            case R.id.cell_2_1:
                playHumanMove(new Point(2, 1), R.id.cell_2_1);
                break;

            case R.id.cell_2_2:
                playHumanMove(new Point(2, 2), R.id.cell_2_2);
                break;
        }
    }

    /**
     * this method resets the cells and create a new board
     */
    private void reset() {
        Log.i(TAG, "reset");
        enableDisableView(mTableLayout, true);

        mCell1.setText("");
        mCell2.setText("");
        mCell3.setText("");
        mCell4.setText("");
        mCell5.setText("");
        mCell6.setText("");
        mCell7.setText("");
        mCell8.setText("");
        mCell9.setText("");

        enableDisableView(mTableLayout, false);
        mCrossButton.setEnabled(true);
        mNoughtButton.setEnabled(true);

        mBoard = new Board();
    }

    /**
     * this method plays human move and an ai move follows
     */
    private void playHumanMove(Point point, int id) {
        if (mBoard.setMove(point, humanPlayer.getSeed())) {
            TextView cell = (TextView) findViewById(id);
            cell.setText(String.valueOf(humanPlayer.getSeed().getSymbol()));
            playAImove(aiplayer.getBestMove(mBoard));
            Log.i(TAG, "fucked up move played");
        } else {
            Log.i(TAG, "human move failed");
        }

        // ai never looses, so skipped unnecessary checks, if game is over its always draw
        if (mBoard.isGameOver()) {
            Toast.makeText(getApplicationContext(), "Drawn", Toast.LENGTH_SHORT);
            enableDisableView(mTableLayout, false);
        }
    }

    private void playAImove(Point bestMove) {
        if (bestMove.equals(0, 0)) {
            mCell1.setText(String.valueOf(aiplayer.getSeed().getSymbol()));
        } else if (bestMove.equals(0, 1)) {
            mCell2.setText(String.valueOf(aiplayer.getSeed().getSymbol()));
        } else if (bestMove.equals(0, 2)) {
            mCell3.setText(String.valueOf(aiplayer.getSeed().getSymbol()));
        } else if (bestMove.equals(1, 0)) {
            mCell4.setText(String.valueOf(aiplayer.getSeed().getSymbol()));
        } else if (bestMove.equals(1, 1)) {
            mCell5.setText(String.valueOf(aiplayer.getSeed().getSymbol()));
        } else if (bestMove.equals(1, 2)) {
            mCell6.setText(String.valueOf(aiplayer.getSeed().getSymbol()));
        } else if (bestMove.equals(2, 0)) {
            mCell7.setText(String.valueOf(aiplayer.getSeed().getSymbol()));
        } else if (bestMove.equals(2, 1)) {
            mCell8.setText(String.valueOf(aiplayer.getSeed().getSymbol()));
        } else if (bestMove.equals(2, 2)) {
            mCell9.setText(String.valueOf(aiplayer.getSeed().getSymbol()));
        }

        mBoard.setMove(bestMove, aiplayer.getSeed());
        if (mBoard.hasPlayerWon(aiplayer.getSeed())) {
            Toast.makeText(getApplicationContext(), "you lost", Toast.LENGTH_SHORT);
            enableDisableView(mTableLayout, false);
        }
    }


    private void setUpGame(char seedSymbol) {
        humanPlayer = new HumanPlayer(new Seed(seedSymbol));
        aiplayer = new AIplayer(humanPlayer.getAdversarySeed());
        mBoard = new Board();
    }
}
