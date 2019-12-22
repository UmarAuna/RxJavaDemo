package com.rxjavademo.RXJavaDemo4MVP.networking.utils;


import com.rxjavademo.RXJavaDemo4MVP.networking.adapter.RetrofitAdapter;
import com.rxjavademo.RXJavaDemo4MVP.networking.api.CropsService;
import com.rxjavademo.RXJavaDemo4MVP.utils.MyApp;

public class NetworkingUtils {

    private static CropsService cropsService;

    public static CropsService getCropsApiInstance(){
        if(cropsService == null)
            cropsService = RetrofitAdapter.getCacheEnabledRetrofit(MyApp.getContext()).create(CropsService.class);
        return cropsService;
    }
}
