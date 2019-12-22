package com.rxjavademo.Dagger2Demo2;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/*
* This is where dagger keep will keep track of the dependencies. It must be annotated with @Module.
* So dagger knows this is a module. Later on, we will create module every feature we build.
* In such module Dagger will look for variable methods and possible instance provider.
* */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application){
        this.application = application;
    }

   /*
   * The methods that exposed the available return type should also be annotated with @Provides decorators.
   * The @Singleton annotation also signals to Dagger compiler that the instance should be created only once in the application.
   * For example, we are specifying the context that uses singleton annotations that can be part of the dependency list.
   *
   * */
    @Provides
    @Singleton
    public Context provideContext(){
        return application;
    }
}
