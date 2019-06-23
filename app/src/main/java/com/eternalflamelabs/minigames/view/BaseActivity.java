package com.eternalflamelabs.minigames.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.eternalflamelabs.minigames.R;
import com.eternalflamelabs.minigames.util.SPTag;
import com.eternalflamelabs.minigames.util.SharedPreferencesManager;

public abstract class BaseActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    protected Context mContext;
    public boolean haveConnectedWifi = false;
    public boolean haveConnectedMobile = false;
    private boolean WIFI_TOGGLE = true;

    public NavigationView getNavigationView() {
        return navigationView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WIFI_TOGGLE = new SharedPreferencesManager(MGCApplication.getContext()).retrieveBoolean(SPTag.TOGGLE_WIFI, true);
        mContext = BaseActivity.this;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setUpNav() {
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.isChecked())
                    menuItem.setChecked(false);
                else
                    menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                Intent intent;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        intent= new Intent(mContext, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Bundle home = new Bundle();
                        startActivity(intent);
                        return true;
                    case R.id.nav_settings:
                        intent = new Intent(mContext, SettingsActivity.class);
                        Bundle settings = new Bundle();
                        startActivity(intent);
                        return true;
                    case R.id.nav_profile:
                        intent = new Intent(mContext, SettingsActivity.class);
                        Bundle profile = new Bundle();
                        startActivity(intent);
                        return true;
                    case R.id.nav_project:
                        intent = new Intent(mContext, SettingsActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_about:
                        intent = new Intent(mContext, SettingsActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_news:
                        intent = new Intent(mContext, SettingsActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_privacy:
                        if (haveConnectedWifi || haveConnectedMobile) {
                            intent = new Intent(mContext, SettingsActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(mContext, R.string.not_connected, Toast.LENGTH_LONG).show();
                        }
                        return true;
                    case R.id.nav_help:
                        if (haveConnectedWifi || haveConnectedMobile) {
                            intent = new Intent(mContext, SettingsActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(mContext, R.string.not_connected, Toast.LENGTH_LONG).show();
                        }
                        return true;
                }
                return false;
            }
        });
        drawerToggle.syncState();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setUpNav();

        drawerToggle.syncState();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isOnline();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @VisibleForTesting
    private ProgressDialog mProgressDialog;

    void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

    protected boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    if (activeNetwork.isConnected())
                        haveConnectedWifi = true;
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    if (activeNetwork.isConnected())
                        haveConnectedMobile = true;
                }
            }
        }
        return haveConnectedWifi || (haveConnectedMobile && !WIFI_TOGGLE);
    }
}