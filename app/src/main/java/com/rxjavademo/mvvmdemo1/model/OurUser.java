package com.rxjavademo.mvvmdemo1.model;

import android.util.Patterns;

public class OurUser {
    private String email, password;

    public OurUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        if (email == null){
            return "";
        }
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        if(password == null){
            return "";
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  boolean isEmailValid(){
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isPasswordLengthGreaterThan5(){
        return getPassword().length() > 5;
    }

}
