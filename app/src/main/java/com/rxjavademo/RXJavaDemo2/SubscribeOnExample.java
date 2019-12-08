package com.rxjavademo.RXJavaDemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.rxjavademo.R;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SubscribeOnExample extends AppCompatActivity {
    private static final String[] STATES = {"Lagos", "Abuja", "Abia", "Edo", "Enugu", "Niger", "Anambra"};
    private Disposable mDisposable= null;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_on_example);

        //Demo 1
        Observable.fromArray(STATES)
                .doOnNext(color -> Log.i("ACTivity", color + " " + Thread.currentThread()))
                .observeOn(Schedulers.io())
                .map(String::length)
                 .subscribe (length -> Log.i("ACTivity", length + " " + Thread.currentThread()));


        //Demo 2
        Observable.fromArray(STATES)
                .doOnNext(color -> Log.i("ACTivity", color + " " + Thread.currentThread()))
                .observeOn(Schedulers.io())
                .map(String::length)
                .subscribeOn(Schedulers.computation())
                .subscribe(length -> Log.i("ACTivity", length + " " + Thread.currentThread()));

        Observable<String> observable = Observable.create(dataSource())
                //If you don't specify the .subscribeOn() method after the Observable.create() method,
                // it will be executed on the current thread—which in our case is the main thread, thereby blocking the app UI.
                //You should only have one subscribeOn() in the Observable chain; adding another one anywhere in the chain will have no effect at all.
                // The recommended place to put this operator is as close to the source as possible for the sake of clarity. In other words, place it first in the operator chain.
                /*
                * Observable.create(dataSource())
                .subscribeOn(Schedulers.computation()) // this has effect
                .subscribeOn(Schedulers.io()) // has no effect
                .doOnNext(s -> {
                    saveToCache(s); // executed on Schedulers.computation()
                })
                *
                * */
                .subscribeOn(Schedulers.newThread())
                .doOnComplete(() -> Log.d("MainActivity", "Complete"));

        mDisposable= observable.subscribe(s ->{
            Log.d("MainActivity", "received " + s + " on thread " + Thread.currentThread().getName());
        });

    }

    private ObservableOnSubscribe<String> dataSource(){
        return (emitter -> {
            for(String state: STATES){
                emitter.onNext(state);
                Log.d("MainActivity", "emitting " + state + " on thread " + Thread.currentThread().getName());
                Thread.sleep(600);
            }
            emitter.onComplete();
        });
    }

    @Override
    protected void onDestroy() {
        if(mDisposable!=null && !mDisposable.isDisposed()){
            mDisposable.dispose();
        }
        super.onDestroy();
    }
}
