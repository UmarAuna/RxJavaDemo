package com.rxjavademo.Dagger2Demo3MVP.di.module;

import com.rxjavademo.Dagger2Demo3MVP.di.scopes.ActivityScope;
import com.rxjavademo.Dagger2Demo3MVP.mvp.contract.Dagger2Demo3MVPContract;
import com.rxjavademo.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class Dagger2Demo3MVP_MvpModule {
    private final Dagger2Demo3MVPContract.View mView;

    public Dagger2Demo3MVP_MvpModule( Dagger2Demo3MVPContract.View mView){
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    Dagger2Demo3MVPContract.View provideVIew(){
        return mView;
    }
}
