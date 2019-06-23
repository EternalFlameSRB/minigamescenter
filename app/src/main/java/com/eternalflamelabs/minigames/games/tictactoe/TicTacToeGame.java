package com.eternalflamelabs.minigames.games.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.eternalflamelabs.minigames.R;

public class TicTacToeGame extends AppCompatActivity {

    private ImageView[] mBlocks;
    private TextView mDisplay, player1tv, player2tv;

    private enum TURN {CIRCLE, CROSS}
    private enum PLAYER {PLAYERONE, PLAYERTWO}

    private TURN mTurn;
    private PLAYER mPlayer;
    private int mStatusCounter = 0;
    private int type;

    private int player1win, player2win, noplayerwin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_tictactoe);

        Intent intent = getIntent();
        type = intent.getIntExtra("type", 9);
        mBlocks = new ImageView[type];
        initialize();

        quickGameStatistic();

        Button btn_newGame = findViewById(R.id.newGameBtn);
        btn_newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void newGame(){
        mStatusCounter = 0;
        enableAll();
        mTurn = TURN.CROSS;
    }

    private void initialize() {
        mDisplay = findViewById(R.id.titleText);

        for (int position = 0; position < type; position++) {
            if (type == 9) {
                GridLayout normal = findViewById(R.id.normal_game);
                normal.setVisibility(View.VISIBLE);
                int resId = getResources().getIdentifier("block_" + (position + 1), "id", getPackageName());
                mBlocks[position] = findViewById(resId);
            } else if (type == 16){
                GridLayout extended = findViewById(R.id.extended_game);
                extended.setVisibility(View.VISIBLE);
                int resId = getResources().getIdentifier("extended_" + (position + 1), "id", getPackageName());
                mBlocks[position] = findViewById(resId);
            } else if (type == 25){
                GridLayout huge = findViewById(R.id.huge_game);
                huge.setVisibility(View.VISIBLE);
                int resId = getResources().getIdentifier("huge_" + (position + 1), "id", getPackageName());
                mBlocks[position] = findViewById(resId);
            }
            final int finalPosition = position;
            mBlocks[position].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchPlayer();
                    switchTurn(finalPosition);
                }
            });
        }
    }

    private void switchTurn(int position) {
        if (mTurn == TURN.CIRCLE) {
            mBlocks[position].setImageResource(R.drawable.circle);
            mBlocks[position].setId(TicTacToeLogic.CIRCLE);
            mTurn = TURN.CROSS;
        } else {
            mBlocks[position].setImageResource(R.drawable.cross);
            mBlocks[position].setId(TicTacToeLogic.CROSS);
            mTurn = TURN.CIRCLE;
        }
        mBlocks[position].setEnabled(false);
        mStatusCounter++;
        if (TicTacToeLogic.isCompleted(position + 1, mBlocks, type)) {
//            mDisplay.setText(TicTacToeLogic.sWinner + " won");
            if(TicTacToeLogic.sWinner.equals("CIRCLE")){
                player2win++;
                mDisplay.setText(getResources().getString(R.string.player_two_wins));
                quickGameStatistic();
            } else if(TicTacToeLogic.sWinner.equals("CROSS")){
                player1win++;
                mDisplay.setText(getResources().getString(R.string.player_one_wins));
                quickGameStatistic();
            }
            displayStick(TicTacToeLogic.sSet, true);


            disableAll();
        }else if (mStatusCounter==type){
            mDisplay.setText(getResources().getString(R.string.game_is_draw));
            noplayerwin++;
            quickGameStatistic();
        }
    }

    private void switchPlayer() {
        if (mPlayer == PLAYER.PLAYERTWO) {
            mPlayer = PLAYER.PLAYERONE;
            mTurn = TURN.CIRCLE;
            mDisplay.setText(getResources().getString(R.string.player_one_turn));
        } else {
            mPlayer = PLAYER.PLAYERTWO;
            mTurn = TURN.CROSS;
            mDisplay.setText(getResources().getString(R.string.player_two_turn));
        }
    }

    private void displayStick(int[] set, boolean show_stick) {
        if (show_stick){
            mBlocks[set[0] - 1].setBackgroundResource(R.color.statsgray);
            mBlocks[set[1] - 1].setBackgroundResource(R.color.statsgray);
            mBlocks[set[2] - 1].setBackgroundResource(R.color.statsgray);
        } else {
            if (set[2] != 0) {
                mBlocks[set[0] - 1].setBackgroundResource(R.drawable.main_bg);
                mBlocks[set[1] - 1].setBackgroundResource(R.drawable.main_bg);
                mBlocks[set[2] - 1].setBackgroundResource(R.drawable.main_bg);
            }
        }
    }

    private void disableAll() {
        for (int i = 0; i < type; i++)
            mBlocks[i].setEnabled(false);
    }

    private void enableAll() {
        displayStick(TicTacToeLogic.sSet, false);
        for (int i = 0; i < type; i++) {
            mBlocks[i].setEnabled(true);
            mBlocks[i].setImageResource(0);
            mBlocks[i].setId(View.NO_ID);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void quickGameStatistic() {
        TextView draw_score_tv;

        player1tv = findViewById(R.id.player1score);
        assert player1tv != null;
        player1tv.setText(String.valueOf(player1win));

        player2tv = findViewById(R.id.player2score);
        assert player2tv != null;
        player2tv.setText(String.valueOf(player2win));

        draw_score_tv = findViewById(R.id.drawscore);
        assert draw_score_tv != null;
        draw_score_tv.setText(String.valueOf(noplayerwin));
    }
}