package com.rxjavademo.databindingdemo3.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;
import com.rxjavademo.R;


public class Url{

	@SerializedName("small")
	private String small;

	@SerializedName("large")
	private String large;

	@SerializedName("medium")
	private String medium;

	public void setSmall(String small){
		this.small = small;
	}

	public String getSmall(){
		return small;
	}

	public void setLarge(String large){
		this.large = large;
	}

	public String getLarge(){
		return large;
	}

	public void setMedium(String medium){
		this.medium = medium;
	}

	public String getMedium(){
		return medium;
	}

	@BindingAdapter({"medium"})
	public static void loadImage(ImageView imageView, String imageURL){
		Glide.with(imageView.getContext())
				.setDefaultRequestOptions(new RequestOptions().circleCrop())
				.load(imageURL)
				.placeholder(R.drawable.ic_name)
				.into(imageView);
	}

	@Override
 	public String toString(){
		return 
			"Url{" + 
			"small = '" + small + '\'' + 
			",large = '" + large + '\'' + 
			",medium = '" + medium + '\'' + 
			"}";
		}
}