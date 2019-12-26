package com.rxjavademo.databindingdemo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.rxjavademo.R;
import com.rxjavademo.databinding.ActivityDatabindingDemo2Binding;

public class DatabindingDemo2 extends AppCompatActivity {
    //Databinding, Glide
    //https://androidwave.com/loading-images-using-data-binding/
    ActivityDatabindingDemo2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_demo2);

        binding.setUsers(
                new Users(
                        "Umar Saidu Auna",
                        "Kano, Nigeria"
                )
        );

        binding.setImageUrl("https://pbs.twimg.com/profile_images/1188457885598208000/OH_skWqv_400x400.jpg");
    }
}
