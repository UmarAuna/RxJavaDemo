package com.rxjavademo.RXJavaDemo3.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Results{

	@SerializedName("results")
	private List<CropsItem> results;

	public void setResults(List<CropsItem> results){
		this.results = results;
	}

	public List<CropsItem> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"Results{" + 
			"results = '" + results + '\'' + 
			"}";
		}
}