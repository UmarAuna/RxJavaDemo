package com.rxjavademo.RXJavaDemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

import com.rxjavademo.R;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class FlatMapOperatorExample extends AppCompatActivity {

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_map_operator_example);

        //flatMap(): Transform the items emitted by an Observable into Observables, then flatten the emissions from those into a single Observable.
        final String[] states = {"Lagos", "Abuja", "Imo", "Enugu"};
        Observable<String> statesObservable = Observable.fromArray(states);

        statesObservable.flatMap(s -> Observable.create(getPopulation(s))
                .subscribeOn(Schedulers.io()))
                .subscribe(pair -> Log.d("MainActivity", pair.first + " population is " + pair.second));

    }

    private ObservableOnSubscribe<Pair> getPopulation(String state){
        return (emitter -> {
            Random r =new Random();
            Log.d("MainActivity", "getPopulation() for " + state + " called on " + Thread.currentThread().getName());
            emitter.onNext(new Pair<>(state, r.nextInt(300000 - 10000) + 10000));
            emitter.onComplete();
        });
    }
}
