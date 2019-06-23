package com.eternalflamelabs.minigames.view;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.eternalflamelabs.minigames.R;
import com.eternalflamelabs.minigames.presenter.HomeAdapter;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        Menu menuNav = navigationView.getMenu();
        MenuItem nav_item = menuNav.findItem(R.id.nav_home);
        navigationView.setCheckedItem(R.id.nav_home);
        nav_item.setEnabled(false);

        GridView gridView = findViewById(R.id.gridview);
        gridView.setAdapter(new HomeAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        openActivity(view);
                        return;
                    case 1:
                        openTwoPlayersActivity(view);
                        return;
                    case 2:
                        openActivity(view);
                        return;
                    case 3:
                        openActivity(view);
                        return;
                    default:
                }
            }
        });
    }

    public void openActivity(View view) {

        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openTwoPlayersActivity(View view) {

        Intent intent = new Intent(this, TwoPlayersActivity.class);
        startActivity(intent);
    }
}
