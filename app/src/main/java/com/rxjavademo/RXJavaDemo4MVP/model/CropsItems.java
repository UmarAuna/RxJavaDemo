package com.rxjavademo.RXJavaDemo4MVP.model;

import com.google.gson.annotations.SerializedName;

public class CropsItems {
    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString(){
        return
                "CropsItem{" +
                        "name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}
