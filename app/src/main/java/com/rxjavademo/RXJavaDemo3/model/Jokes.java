package com.rxjavademo.RXJavaDemo3.model;


import com.google.gson.annotations.SerializedName;


public class Jokes{

	@SerializedName("punchline")
	private String punchline;

	@SerializedName("setup")
	private String setup;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private String type;

	public void setPunchline(String punchline){
		this.punchline = punchline;
	}

	public String getPunchline(){
		return punchline;
	}

	public void setSetup(String setup){
		this.setup = setup;
	}

	public String getSetup(){
		return setup;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"Jokes{" + 
			"punchline = '" + punchline + '\'' + 
			",setup = '" + setup + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}