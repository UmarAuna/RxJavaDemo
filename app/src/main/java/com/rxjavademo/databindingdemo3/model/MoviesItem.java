package com.rxjavademo.databindingdemo3.model;


import com.google.gson.annotations.SerializedName;


public class MoviesItem{

	@SerializedName("name")
	private String name;

	@SerializedName("genre")
	private String genre;

	@SerializedName("url")
	private Url url;

	@SerializedName("timestamp")
	private String timestamp;

	public void setName(String name){
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getName(){
		return name;
	}

	public void setUrl(Url url){
		this.url = url;
	}

	public Url getUrl(){
		return url;
	}

	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}

	public String getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return 
			"MoviesItem{" + 
			"name = '" + name + '\'' + 
			",url = '" + url + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}