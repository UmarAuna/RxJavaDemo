package com.rxjavademo.Dagger2Demo3MVP.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.rxjavademo.Dagger2Demo3MVP.di.component.ApplicationComponent;
import com.rxjavademo.Dagger2Demo3MVP.di.component.DaggerApplicationComponent;
import com.rxjavademo.Dagger2Demo3MVP.di.module.ContextModule;

public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);
    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    //Rebuild project here
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
