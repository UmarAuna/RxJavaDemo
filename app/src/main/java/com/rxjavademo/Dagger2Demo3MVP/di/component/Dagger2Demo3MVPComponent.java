package com.rxjavademo.Dagger2Demo3MVP.di.component;


import android.content.Context;

import com.rxjavademo.Dagger2Demo3MVP.di.module.AdapterModule;
import com.rxjavademo.Dagger2Demo3MVP.di.module.Dagger2Demo3MVP_MvpModule;
import com.rxjavademo.Dagger2Demo3MVP.di.qualifier.ActivityContext;
import com.rxjavademo.Dagger2Demo3MVP.di.scopes.ActivityScope;
import com.rxjavademo.Dagger2Demo3MVP.ui.Dagger2Demo3MVP;

import dagger.Component;

@ActivityScope
@Component(modules = {AdapterModule.class, Dagger2Demo3MVP_MvpModule.class}, dependencies = ApplicationComponent.class)
public interface Dagger2Demo3MVPComponent {
    //Components are what inject the dependencies from the Modules.

    @ActivityContext
    Context getContext();
    void injectDagger2Demo3MVP(Dagger2Demo3MVP dagger2Demo3MVP);


}
