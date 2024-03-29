package com.eternalflamelabs.minigames.games.connect4;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.eternalflamelabs.minigames.R;
import com.eternalflamelabs.minigames.games.connect4.controller.GamePlayController;
import com.eternalflamelabs.minigames.games.connect4.rules.GameRules;
import com.eternalflamelabs.minigames.games.connect4.view.BoardView;

public class GamePlayActivity extends AppCompatActivity {

    private GamePlayController mGameController;
    private final GameRules mGameRules = new GameRules();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_connect_four);

        BoardView boardView = findViewById(R.id.gameView);
        mGameRules.importFrom(getIntent().getExtras());
        mGameController = new GamePlayController(this, boardView, mGameRules);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.about);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showAlert(R.string.back);
                break;
            case R.id.restart:
                showAlert(R.string.reset_game);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert(final int msgId) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage(msgId)
                .setCancelable(false)
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (msgId == R.string.back) {
                            mGameController.exitGame();
                        } else {
                            mGameController.restartGame();
                        }
                    }
                }).show();
    }

    @Override
    public void onBackPressed() {
        showAlert(R.string.back);
    }
}
