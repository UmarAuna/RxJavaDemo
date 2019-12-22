package com.rxjavademo.Dagger2Demo2;

import android.app.Application;

public class App extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        //needs to run once to generate it
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    //We also defined public getComponent() methods which will return AppComponent instance
    public AppComponent getComponent() {
        return component;
    }
}
