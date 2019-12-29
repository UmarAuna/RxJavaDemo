package com.rxjavademo.mvvmdemo1.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rxjavademo.R;
import com.rxjavademo.databinding.ActivityMvvmdemo1Binding;
import com.rxjavademo.mvvmdemo1.viewmodel.LoginViewModel;

public class MVVMDemo1 extends AppCompatActivity {
    //https://androidwave.com/mvvm-architecture-app-in-android/
    //https://www.journaldev.com/20292/android-mvvm-design-pattern
    //https://medium.com/@nguyenthecoder/guidelines-to-implement-the-mvvm-pattern-in-android-8ac5e306c6f7

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmdemo1Binding activityMvvmdemo1Binding = DataBindingUtil.setContentView(this,R.layout.activity_mvvmdemo1);
        activityMvvmdemo1Binding.setViewModel(new LoginViewModel());
        activityMvvmdemo1Binding.executePendingBindings();
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message){
        if(message != null){
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}
