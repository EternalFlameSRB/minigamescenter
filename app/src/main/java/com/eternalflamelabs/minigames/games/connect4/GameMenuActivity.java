package com.eternalflamelabs.minigames.games.connect4;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;

import com.eternalflamelabs.minigames.R;
import com.eternalflamelabs.minigames.constants.URLVar;
import com.eternalflamelabs.minigames.games.connect4.controller.GameMenuController;
import com.eternalflamelabs.minigames.games.connect4.rules.GameRules;
import com.eternalflamelabs.minigames.games.connect4.view.MenuView;
import com.eternalflamelabs.minigames.view.BaseActivity;

public class GameMenuActivity extends BaseActivity implements GameMenuController.MenuControllerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_connect_four_menu);
        MenuView menuView = findViewById(R.id.menuView);
        GameMenuController gameMenuController =new GameMenuController(this, menuView);
        menuView.setListeners(gameMenuController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.restart);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about) {
            showAlert();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPlay(@NonNull GameRules gameRules) {
        Intent gamePlayIntent = new Intent(this,GamePlayActivity.class);
        gamePlayIntent.putExtras(gameRules.exportTo(new Bundle()));
        startActivity(gamePlayIntent);
    }

    private void showAlert() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage(R.string.open_source_game)
                .setCancelable(false)
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.visit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URLVar.REPO_CONNECT_FOUR)));
                        } catch (android.content.ActivityNotFoundException exception) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URLVar.REPO_CONNECT_FOUR)));
                        }
                    }
                }).show();
    }
}
