package com.rxjavademo.mvvmdemo1.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rxjavademo.R;
import com.rxjavademo.databinding.ActivityMvvmdemo1Binding;
import com.rxjavademo.mvvmdemo1.model.OurUser;
import com.rxjavademo.mvvmdemo1.viewmodel.LoginViewModelNew;
import com.rxjavademo.mvvmdemo1.viewmodel.LoginViewModelOld;

public class MVVMDemo1 extends AppCompatActivity {
    //https://androidwave.com/mvvm-architecture-app-in-android/
    //https://www.journaldev.com/20292/android-mvvm-design-pattern
    //https://www.journaldev.com/22561/android-mvvm-livedata-data-binding
    //https://medium.com/@nguyenthecoder/guidelines-to-implement-the-mvvm-pattern-in-android-8ac5e306c6f7
    //https://android.jlelse.eu/dive-deep-into-androids-viewmodel-android-architecture-components-e0a7ded26f70
    //https://android.jlelse.eu/mvvm-dos-and-don-ts-5950d6f347d4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmdemo1Binding activityMvvmdemo1Binding = DataBindingUtil.setContentView(this,R.layout.activity_mvvmdemo1);
        LoginViewModelNew loginViewModelNew= ViewModelProviders.of(this).get(LoginViewModelNew.class);
        activityMvvmdemo1Binding.setViewModelNew(loginViewModelNew);
        activityMvvmdemo1Binding.setLifecycleOwner(this);

        loginViewModelNew.getUser().observe(this, new Observer<OurUser>() {
            @Override
            public void onChanged(OurUser ourUser) {
                if (ourUser.getEmail().length() > 0 || ourUser.getPassword().length() > 0)
                    Toast.makeText(getApplicationContext(), "email : " + ourUser.getEmail() + " password " + ourUser.getPassword(), Toast.LENGTH_SHORT).show();
            }
        });



    }

}
