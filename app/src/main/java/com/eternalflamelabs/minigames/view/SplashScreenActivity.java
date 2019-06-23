package com.eternalflamelabs.minigames.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eternalflamelabs.minigames.R;
import com.eternalflamelabs.minigames.constants.Constants;
import com.eternalflamelabs.minigames.presenter.DownloadJson;
import com.eternalflamelabs.minigames.util.SharedPreferencesManager;

import java.util.Locale;

public class SplashScreenActivity extends AppCompatActivity {

    private SharedPreferencesManager shared;
    private ProgressBar progressBar;
    private TextView tv_loading;
    public String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shared = new SharedPreferencesManager(MGCApplication.getContext());
        setContentView(R.layout.activity_splash);

        language = String.valueOf(Locale.getDefault().getLanguage());
        if (language.equals(Constants.EN)) {
            shared.storeString(Constants.LANGUAGE, Constants.EN);
        } else {
            shared.storeString(Constants.LANGUAGE, Constants.SR);
        }
        progressBar = findViewById(R.id.progress_bar);
        tv_loading = findViewById(R.id.loading_text);

        new GetJson().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class GetJson extends DownloadJson {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv_loading.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            tv_loading.setText(R.string.please_wait);
            progressBar.setVisibility(View.INVISIBLE);

            shared.storeBoolean(Constants.NEW_SESSION, true);
            boolean first_time = shared.retrieveBoolean(Constants.FIRST_TIME, true);
            if (first_time) {
                shared.storeBoolean(Constants.FIRST_TIME, true);
            }

            Intent i = new Intent(SplashScreenActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }
    }

}
