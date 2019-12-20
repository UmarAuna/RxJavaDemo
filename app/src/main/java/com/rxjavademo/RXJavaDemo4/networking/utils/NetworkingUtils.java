package com.rxjavademo.RXJavaDemo4.networking.utils;


import android.content.Context;

import com.rxjavademo.RXJavaDemo4.networking.adapter.RetrofitAdapter;
import com.rxjavademo.RXJavaDemo4.networking.api.CropsService;
import com.rxjavademo.RXJavaDemo4.utils.MyApp;

public class NetworkingUtils {

    private static CropsService cropsService;

    public static CropsService getCropsApiInstance(){
        if(cropsService == null)
            cropsService = RetrofitAdapter.getCacheEnabledRetrofit(MyApp.getContext()).create(CropsService.class);
        return cropsService;
    }






}
