package com.rxjavademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rxjavademo.Dagger2Demo1.Dagger2Demo1;
import com.rxjavademo.Dagger2Demo2.Dagger2Demo2;
import com.rxjavademo.Dagger2Demo3MVP.ui.Dagger2Demo3MVP;
import com.rxjavademo.databindingdemo1.DatabindingDemo1;
import com.rxjavademo.RXJavaDemo1.RXJavaDemo1;
import com.rxjavademo.RXJavaDemo2.RXJavaDemo2;
import com.rxjavademo.RXJavaDemo3.ui.RXJavaDemo3;
import com.rxjavademo.RXJavaDemo4MVP.ui.RXJavaDemo4;
import com.rxjavademo.databindingdemo2.DatabindingDemo2;
import com.rxjavademo.databindingdemo3.ui.DatabindingDemo3;

public class ListMainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        String [] projects =
                {"RXJavaDemo", "RXJavaDemo1", "RXJavaDemo2", "RXJavaDemo3", "RXJavaDemo4MVP","Dagger2Demo1", "Dagger2Demo2","Dagger2Demo3MVP", "DataBindingDemo1","DataBindingDemo2", "DataBindingDemo3"};

        listView = findViewById(R.id.list_view);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ListMainActivity.this, android.R.layout.simple_list_item_1,projects);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: startActivity(new Intent(ListMainActivity.this, MainActivity.class));
                    break;
                    case 1: startActivity(new Intent(ListMainActivity.this, RXJavaDemo1.class));
                    break;
                    case 2: startActivity(new Intent(ListMainActivity.this, RXJavaDemo2.class));
                    break;
                    case 3: startActivity(new Intent(ListMainActivity.this, RXJavaDemo3.class));
                    break;
                    case 4: startActivity(new Intent(ListMainActivity.this, RXJavaDemo4.class));
                    break;
                    case 5: startActivity(new Intent(ListMainActivity.this, Dagger2Demo1.class));
                    break;
                    case 6: startActivity(new Intent(ListMainActivity.this, Dagger2Demo2.class));
                    break;
                    case 7: startActivity(new Intent(ListMainActivity.this, Dagger2Demo3MVP.class));
                    break;
                    case 8: startActivity(new Intent(ListMainActivity.this, DatabindingDemo1.class));
                    break;
                    case 9: startActivity(new Intent(ListMainActivity.this, DatabindingDemo2.class));
                    break;
                    case 10: startActivity(new Intent(ListMainActivity.this, DatabindingDemo3.class));
                    break;

                }
            }
        });

    }
}