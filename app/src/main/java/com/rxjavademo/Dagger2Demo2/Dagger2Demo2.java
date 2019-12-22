package com.rxjavademo.Dagger2Demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.rxjavademo.R;

import javax.inject.Inject;

public class Dagger2Demo2 extends AppCompatActivity {
    //https://androidwave.com/dagger2-android-example/
    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2_demo2);
        ((App) getApplication()).getComponent().inject(this);
    }
}
