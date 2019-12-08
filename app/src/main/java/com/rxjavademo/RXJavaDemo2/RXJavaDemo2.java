package com.rxjavademo.RXJavaDemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.rxjavademo.R;
import com.rxjavademo.RXJavaDemo1.BooksActivity;
import com.rxjavademo.RXJavaDemo1.ColorsActivity;
import com.rxjavademo.RXJavaDemo1.RXJavaDemo1;
import com.rxjavademo.RXJavaDemo1.RxJavaSimpleActivity;
import com.rxjavademo.RXJavaDemo1.SchedulerActivity;

public class RXJavaDemo2 extends AppCompatActivity {
Button first, second, third;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo2);
        first = findViewById(R.id.first_2);
        second = findViewById(R.id.second_2);
        third = findViewById(R.id.third_2);

        first.setOnClickListener(v -> startActivity(new Intent(RXJavaDemo2.this, SubscribeOnExample.class)));
        second.setOnClickListener(v -> startActivity(new Intent(RXJavaDemo2.this, ObserveOnExample.class)));
        third.setOnClickListener(v -> startActivity(new Intent(RXJavaDemo2.this, FlatMapOperatorExample.class)));

    }
}
