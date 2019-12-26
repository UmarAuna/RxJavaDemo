package com.rxjavademo.databindingdemo3.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class MoviesResponse{

	@SerializedName("Movies")
	private List<MoviesItem> movies;

	public void setMovies(List<MoviesItem> movies){
		this.movies = movies;
	}

	public List<MoviesItem> getMovies(){
		return movies;
	}

	@Override
 	public String toString(){
		return 
			"MoviesResponse{" + 
			"movies = '" + movies + '\'' + 
			"}";
		}
}