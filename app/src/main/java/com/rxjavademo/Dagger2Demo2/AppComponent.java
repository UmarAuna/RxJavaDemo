package com.rxjavademo.Dagger2Demo2;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(Dagger2Demo2 dagger2Demo2);
}
