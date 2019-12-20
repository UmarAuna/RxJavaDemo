package com.rxjavademo.RXJavaDemo4.utils;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    private static MyApp instance;

    public static MyApp getInstance(){
        return instance;
    }

    public static Context getContext(){
        //return instance;
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();

    }
}
