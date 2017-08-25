package com.example.toutiao;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangjinbo on 17-8-25.
 */

public class InitApp extends Application {
    public static Context AppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();

        initTheme();

        if(BuildConfig.DEBUG) {

        }
    }

    private void initTheme() {

    }
}
