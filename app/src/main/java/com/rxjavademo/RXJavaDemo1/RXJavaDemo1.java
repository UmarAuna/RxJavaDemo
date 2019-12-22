package com.rxjavademo.RXJavaDemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.rxjavademo.R;

public class RXJavaDemo1 extends AppCompatActivity {
Button first, second, third, fourth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo1);
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);

        first.setOnClickListener(v -> startActivity(new Intent(RXJavaDemo1.this, BooksActivity.class)));
        second.setOnClickListener(v -> startActivity(new Intent(RXJavaDemo1.this, ColorsActivity.class)));
        third.setOnClickListener(v -> startActivity(new Intent(RXJavaDemo1.this, RxJavaSimpleActivity.class)));
        fourth.setOnClickListener(v -> startActivity(new Intent(RXJavaDemo1.this, SchedulerActivity.class)));

    }
}
