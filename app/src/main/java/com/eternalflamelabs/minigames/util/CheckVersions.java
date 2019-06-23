package com.eternalflamelabs.minigames.util;

import android.util.Log;

import com.eternalflamelabs.minigames.R;
import com.eternalflamelabs.minigames.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckVersions {
    private static final String TAG = CheckVersions.class.getSimpleName();
    private static String versions;

    public static String parseVersion(String tempJsonString, String JSON_FIELD) {
        if (tempJsonString != null) {
            try {
                JSONObject jsonObjTrivia = new JSONObject(tempJsonString);
                JSONObject jsonMtwApplication = jsonObjTrivia.getJSONObject(JSON_FIELD);

                versions = jsonMtwApplication.toString();

            } catch (final JSONException e) {
                Log.e(TAG, R.string.json_parsing_error + e.getMessage());
            }
        }
        return versions;
    }

    public static boolean checkApplication(String json, int current) {
        if (json != null) {
            try {
                JSONObject jsonObj = new JSONObject(json);

                int version_application = jsonObj.getInt(Constants.DB_APP);

                if (version_application > current) {
                    return true;
                }

            } catch (final JSONException e) {
                Log.e(TAG, R.string.json_parsing_error + e.getMessage());
            }
        }
        return false;
    }

}
