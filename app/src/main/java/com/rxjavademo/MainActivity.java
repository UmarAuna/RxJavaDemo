package com.rxjavademo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable<String> stringObservable = Observable.just("Maroon", "Red", "Orange", "Yellow", "Green", "White", "Black", "Blue", "Navy");
        stringObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.d("onNext:", "New data received:" +  s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Error received: ", "Error received:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("", "All data emitted");

                    }
                });

        Flowable<String> stringFlowable = Flowable.just("Maroon", "Red", "Orange", "Yellow", "Green", "White", "Black", "Blue", "Navy");
        stringFlowable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<String>() {
                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Single <String> stringSingle = Single.just("Maroon");
        stringSingle.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<String>() {
                    @Override
                    public void onSuccess(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });


    }
}