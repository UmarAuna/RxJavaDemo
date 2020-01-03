package com.rxjavademo.roomdatabasedemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rxjavademo.R;
import com.rxjavademo.roomdatabasedemo1.db.AppDatabase;
import com.rxjavademo.roomdatabasedemo1.db.User;

import java.util.Date;

public class AddNewUser extends AppCompatActivity {

    TextInputEditText textFirstName;
    TextInputEditText textLastName;
    TextInputEditText textEmail;
    TextInputEditText textPhoneNo;
    TextInputEditText textAddress;
    Button buttonAddUser;
    TextInputLayout textInputLayoutFirstName;
    TextInputLayout textInputLayoutEmail;

    AppDatabase database;

    public static void startActivity(Context mContext){
        Intent mItent = new Intent(mContext, AddNewUser.class);
        mContext.startActivity(mItent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);
        textFirstName = findViewById(R.id.textFirstName);
        textLastName = findViewById(R.id.textLastName);
        textEmail = findViewById(R.id.textEmail);
        textPhoneNo = findViewById(R.id.textPhoneNo);
        textAddress = findViewById(R.id.textAddress);
        buttonAddUser = findViewById(R.id.buttonAddUser);
        textInputLayoutFirstName = findViewById(R.id.textInputLayoutFirstName);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);

        database = AppDatabase.getDatabaseInstance(this);

        buttonAddUser.setOnClickListener(v -> {
            if (textFirstName.getText().toString().trim().isEmpty()) {
                textInputLayoutFirstName.setError("Invalid Name");
                return;
            }

            if (textEmail.getText().toString().trim().isEmpty()) {
                textInputLayoutEmail.setError("Invalid Email");
                return;
            }
            User mUser = new User(textFirstName.getText().toString(), textLastName.getText().toString(), textEmail.getText().toString(), textPhoneNo.getText().toString(), textAddress.getText().toString(), new Date(), new Date());
            database.userDao().insertUser(mUser);
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
