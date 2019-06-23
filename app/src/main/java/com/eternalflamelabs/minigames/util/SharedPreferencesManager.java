package com.eternalflamelabs.minigames.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.eternalflamelabs.minigames.R;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesManager {
    /**
     * SharedPreferences to store the settings. This way, they'll be available next time the user starts the app
     */
    private SharedPreferences sPreferences;
    /**
     * Editor to make changes on sharedPreferences
     */
    private SharedPreferences.Editor sEditor;

    public SharedPreferencesManager() {

    }

    public SharedPreferencesManager(Context context) {
        sPreferences = context.getSharedPreferences(context.getString(R.string.shared_preference), MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor() {
        return sPreferences.edit();
    }

    public void storeString(String tag, String str) {
        sEditor = getEditor();
        sEditor.putString(tag, str);
        sEditor.commit();
    }

    /**
     * @param tag    identifies the string
     * @param defStr default string
     * @return the stored or default string
     */

    public String retrieveString(String tag, String defStr) {
        return sPreferences.getString(tag, defStr);
    }

    /**
     * @param tag      identifies the value
     * @param defValue default value
     * @return the stored or default value
     */
    public int retrieveInt(String tag, int defValue) {
        return sPreferences.getInt(tag, defValue);
    }

    /**
     * @param tag      identifies the value
     * @param defValue the value itself
     */
    public void storeInt(String tag, int defValue) {
        sEditor = getEditor();
        sEditor.putInt(tag, defValue);
        sEditor.commit();
    }

    public boolean retrieveBoolean(String tag, boolean defBol) {
        return sPreferences.getBoolean(tag, defBol);
    }

    public void storeBoolean(String tag, boolean defBol) {
        sEditor = getEditor();
        sEditor.putBoolean(tag, defBol);
        sEditor.commit();
    }
}