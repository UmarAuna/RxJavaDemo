package com.rxjavademo.databindingdemo2;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Users {
    private String name, address;

    public Users(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @BindingAdapter("profileHoto")
    public static void  loadImage(ImageView view, String imageUrl){
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().centerInside())
                .into(view);
    }


}
