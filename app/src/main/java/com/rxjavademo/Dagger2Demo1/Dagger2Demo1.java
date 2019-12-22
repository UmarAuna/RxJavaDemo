package com.rxjavademo.Dagger2Demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.rxjavademo.R;

import javax.inject.Inject;

public class Dagger2Demo1 extends AppCompatActivity {
    //https://www.journaldev.com/16758/android-dependeny-injection-dagger
    EditText inUsername, inNumber;
    Button btnSave, btnGet;
    private MyComponent myComponent;

    @Inject
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2_demo1);
        btnGet = findViewById(R.id.btnGet);
        btnSave = findViewById(R.id.btnSave);
        inUsername = findViewById(R.id.inUsername);
        inNumber = findViewById(R.id.inNumber);

        myComponent = DaggerMyComponent.builder().sharedPrefModule(new SharedPrefModule(this)).build();
        myComponent.inject(this);

        btnSave.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", inUsername.getText().toString().trim());
            editor.putString("number", inNumber.getText().toString().trim());
            editor.apply();
        });

        btnGet.setOnClickListener(v ->{
            inUsername.setText(sharedPreferences.getString("username", "default"));
            inNumber.setText(sharedPreferences.getString("number", "12345"));
        });


    }
}
