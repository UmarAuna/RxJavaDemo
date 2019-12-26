package com.rxjavademo.databindingdemo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.rxjavademo.R;
import com.rxjavademo.databinding.ActivityDemo1DatabindingBinding;


public class DatabindingDemo1 extends AppCompatActivity {
    //Databinding
    //https://androidwave.com/data-binding-in-android-tutorial/
    ActivityDemo1DatabindingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_demo1_databinding);

        //Bind with Views and Widget

        //binding.tvName.setText("Umar Saidu Auna");
        //binding.tvAddress.setText("Minna, Nigeria");
        //binding.tvFollowers.setText("240K");
        //binding.tvfollowing.setText("324K");

        //Bind the data object with Views

        binding.setUser(
                new User(
                        "Umar Saidu Auna",
                        "Minna, Nigeria",
                        "240K",
                        "324K"
                )
        );

        binding.setHandler(new EventHandler(this));




    }
}
