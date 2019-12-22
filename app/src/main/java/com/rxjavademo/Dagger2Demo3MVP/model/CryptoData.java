package com.rxjavademo.Dagger2Demo3MVP.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CryptoData {
    @SerializedName("name")
    public String name;
    @SerializedName("capital")
    public String capital;
    @SerializedName("region")
    public String region;
    @SerializedName("population")
    public String population;
    @SerializedName("alpha2code")
    public String alpha2code;
    @SerializedName("alpha3code")
    public String alpha3code;
    @SerializedName("demonym")
    public String demonym;
    @SerializedName("cioc")
    public String cioc;
}






