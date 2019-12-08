package com.rxjavademo.RXJavaDemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rxjavademo.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ObserveOnExample extends AppCompatActivity {

    private Disposable mDisposable = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observe_on_example);

        TextView textView = findViewById(R.id.textView);

        Observable<String> observable = Observable.create(dataSource())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                //.timeout(2, TimeUnit.SECONDS)
                .doOnComplete(() -> Log.d("ObserveOnActivity", "Complete") );


        mDisposable = observable.subscribe(s -> {
            Log.d("ObserveOnActivity", "received " + s + " on thread " + Thread.currentThread().getName());
            textView.setText(s);
        });
    }


    private ObservableOnSubscribe<String> dataSource(){
        return (emitter -> {
            Thread.sleep(800);
            emitter.onNext("Value");
            Log.d("ObserveOnActivity", "dataSource() on thread " + Thread.currentThread().getName());
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
