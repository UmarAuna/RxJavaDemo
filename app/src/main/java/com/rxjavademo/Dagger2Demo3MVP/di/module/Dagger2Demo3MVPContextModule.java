package com.rxjavademo.Dagger2Demo3MVP.di.module;

import android.content.Context;

import com.rxjavademo.Dagger2Demo3MVP.di.qualifier.ActivityContext;
import com.rxjavademo.Dagger2Demo3MVP.di.scopes.ActivityScope;
import com.rxjavademo.Dagger2Demo3MVP.ui.Dagger2Demo3MVP;

import dagger.Module;
import dagger.Provides;

@Module
public class Dagger2Demo3MVPContextModule {
    private Dagger2Demo3MVP dagger2Demo3MVP;

    public Context context;

    public Dagger2Demo3MVPContextModule(Dagger2Demo3MVP dagger2Demo3MVP){
        this.dagger2Demo3MVP = dagger2Demo3MVP;
        context = dagger2Demo3MVP;
    }

    @Provides
    @ActivityScope
    public Dagger2Demo3MVP providesDagger2Demo3MVP(){
        return dagger2Demo3MVP;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext(){
        return context;
    }


}
