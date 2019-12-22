package com.rxjavademo.Dagger2Demo1;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SharedPrefModule.class})
public interface MyComponent {
    void inject(Dagger2Demo1 dagger2Demo1);
}

