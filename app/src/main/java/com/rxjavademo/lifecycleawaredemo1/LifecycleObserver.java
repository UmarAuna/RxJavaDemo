package com.rxjavademo.lifecycleawaredemo1;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

public class LifecycleObserver implements androidx.lifecycle.LifecycleObserver {
    private static final String TAG = "LifecycleObserver";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCeateEvent(){
    Log.i(TAG, "Observer ON_CREATE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStartEvent(){
        Log.i(TAG, "Observer ON_START");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResumeEvent(){
        Log.i(TAG, "Observer ON_RESUME");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPauseEvent(){
        Log.i(TAG, "Observer ON_PAUSE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStopEvent(){
        Log.i(TAG, "Observer ON_STOP");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroyEvent(){
        Log.i(TAG, "Observer ON_DESTROY");
    }
}
