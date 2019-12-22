package com.rxjavademo.Dagger2Demo3MVP.di.module;

import android.content.Context;

import com.rxjavademo.Dagger2Demo3MVP.di.qualifier.ApplicationContext;
import com.rxjavademo.Dagger2Demo3MVP.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext(){
        return context;
    }
}
