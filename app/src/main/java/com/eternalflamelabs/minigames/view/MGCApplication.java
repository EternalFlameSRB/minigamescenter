package com.eternalflamelabs.minigames.view;

import android.app.Application;
import android.content.Context;

public class MGCApplication extends Application {
    // App level variable to retain selected spinner value
    private static MGCApplication instance;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    public static Context getContext(){
        return instance.getApplicationContext();
    }
}

