package com.eternalflamelabs.minigames.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.eternalflamelabs.minigames.R;
import com.eternalflamelabs.minigames.games.connect4.GameMenuActivity;
import com.eternalflamelabs.minigames.games.tictactoe.TicTacToeGame;
import com.eternalflamelabs.minigames.model.ListItem;
import com.eternalflamelabs.minigames.presenter.GameListAdapter;

import java.util.ArrayList;

public class TwoPlayersActivity extends BaseActivity {

    private ListView listView;
    private GameListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);

        TwoPlayersActivity.this.setTitle("Games for 2 Player");

        listView = findViewById(R.id.games_list);
        ArrayList<ListItem> gamesList = new ArrayList<>();

        gamesList.add(new ListItem("Tic Tac Toe", "Play standard game of Tic Tac Toe with another player", R.drawable.main_quick));
        gamesList.add(new ListItem("Tic Tac Toe (extended)", "Play extended game (4x4 tiles) of Tic Tac Toe with another player", R.drawable.main_quick));
        gamesList.add(new ListItem("Tic Tac Toe (huge)", "Play game (5x5 tiles) of Tic Tac Toe with another player", R.drawable.main_quick));
        gamesList.add(new ListItem("Connect 4", "Play standard game of Connect 4 with another player", R.drawable.ic_game_connect_four));

        adapter = new GameListAdapter(TwoPlayersActivity.this, gamesList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent0 = new Intent(TwoPlayersActivity.this, TicTacToeGame.class);
                        intent0.putExtra("type", 9);
                        startActivity(intent0);
                        return;
                    case 1:
                        Intent intent1 = new Intent(TwoPlayersActivity.this, TicTacToeGame.class);
                        intent1.putExtra("type", 16);
                        startActivity(intent1);
                        return;
                    case 2:
                        Intent intent2 = new Intent(TwoPlayersActivity.this, TicTacToeGame.class);
                        intent2.putExtra("type", 25);
                        startActivity(intent2);
                        return;
                    case 3:
                        Intent intent3 = new Intent(TwoPlayersActivity.this, GameMenuActivity.class);
                        intent3.putExtra("type", "2 Players");
                        startActivity(intent3);
                        return;
                    default:
                }
            }
        });
    }
}
