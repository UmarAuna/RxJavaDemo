package com.rxjavademo.databindingdemo1;

import android.content.Context;
import android.widget.Toast;

public class EventHandler {
    Context context;

    public EventHandler(Context context){
        this.context = context;
    }

    public void onButtonClick(String name){
        Toast.makeText(context, "Now you are following " + name, Toast.LENGTH_SHORT).show();
    }
}
