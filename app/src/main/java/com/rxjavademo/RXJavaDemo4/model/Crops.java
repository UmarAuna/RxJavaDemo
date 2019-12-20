package com.rxjavademo.RXJavaDemo4.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Crops {
    @SerializedName("results")
    private List<CropsItems> results;

    public void setResults(List<CropsItems> results){
        this.results = results;
    }

    public List<CropsItems> getResults(){
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
