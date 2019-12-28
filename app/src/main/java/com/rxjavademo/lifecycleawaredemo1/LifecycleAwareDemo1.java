package com.rxjavademo.lifecycleawaredemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.rxjavademo.R;

public class LifecycleAwareDemo1 extends AppCompatActivity {
    //https://androidwave.com/lifecycle-aware-components-architecture-components/
    private static final String TAG = "LifeCycleAwareDemo1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_aware_demo1);
        Log.i(TAG, "Owner onCreate");
        getLifecycle().addObserver(new LifecycleObserver());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Owner onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Owner onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Owner onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Owner onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Owner onDestroy");
    }
}
