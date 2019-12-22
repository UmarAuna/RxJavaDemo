package com.rxjavademo.RXJavaDemo4MVP.callback;

public abstract class Callback<T> {

    public abstract void returnResult(T t);
    public abstract void returnError(String message);
}
