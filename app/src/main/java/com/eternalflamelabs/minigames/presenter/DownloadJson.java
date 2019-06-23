package com.eternalflamelabs.minigames.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.eternalflamelabs.minigames.BuildConfig;
import com.eternalflamelabs.minigames.constants.Constants;
import com.eternalflamelabs.minigames.util.CheckVersions;
import com.eternalflamelabs.minigames.util.SharedPreferencesManager;
import com.eternalflamelabs.minigames.view.MGCApplication;

public abstract class DownloadJson extends AsyncTask<Void, Void, Void> {

    @Override
    final protected Void doInBackground(Void... arg0) {
        Context context = MGCApplication.getContext();
        SharedPreferencesManager shared = new SharedPreferencesManager(context);

        int current_app_version = BuildConfig.VERSION_CODE;
        String language = shared.retrieveString(Constants.LANGUAGE, Constants.EN);

        HttpHandler sh = new HttpHandler();
        String jsonStrVersion;

        if (language.equals(Constants.SR)) {
            jsonStrVersion = sh.makeServiceCall(Constants.URL_VERSION);
        } else {
            jsonStrVersion = sh.makeServiceCall(Constants.URL_VERSION);
        }

        if (jsonStrVersion != null) {
            String jsonVersion = CheckVersions.parseVersion(jsonStrVersion, Constants.FIELD_VERSION);

            if (CheckVersions.checkApplication(jsonVersion, current_app_version)) {
                shared.storeBoolean(Constants.NEEDS_UPDATE, true);
            } else {
                shared.storeBoolean(Constants.NEEDS_UPDATE, false);
            }
        }
        return null;
    }
}
