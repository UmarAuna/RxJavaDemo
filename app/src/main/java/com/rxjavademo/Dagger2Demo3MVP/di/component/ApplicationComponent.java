package com.rxjavademo.Dagger2Demo3MVP.di.component;

import android.content.Context;

import com.rxjavademo.Dagger2Demo3MVP.di.module.ContextModule;
import com.rxjavademo.Dagger2Demo3MVP.di.module.RetrofitModule;
import com.rxjavademo.Dagger2Demo3MVP.di.qualifier.ActivityContext;
import com.rxjavademo.Dagger2Demo3MVP.di.qualifier.ApplicationContext;
import com.rxjavademo.Dagger2Demo3MVP.di.scopes.ApplicationScope;
import com.rxjavademo.Dagger2Demo3MVP.retrofit.APIInterface;
import com.rxjavademo.Dagger2Demo3MVP.util.MyApplication;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {
    //Components are what inject the dependencies from the Modules.

    APIInterface getApiInterface();

    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication myApplication);

}
