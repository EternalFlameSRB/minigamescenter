package com.eternalflamelabs.minigames.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;

import com.eternalflamelabs.minigames.R;

public class SettingsActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        Menu menuNav = navigationView.getMenu();
        MenuItem nav_item = menuNav.findItem(R.id.nav_settings);
        navigationView.setCheckedItem(R.id.nav_settings);
        nav_item.setEnabled(false);
    }
}