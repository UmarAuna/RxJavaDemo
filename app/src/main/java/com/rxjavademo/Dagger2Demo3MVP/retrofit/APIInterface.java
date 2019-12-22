package com.rxjavademo.Dagger2Demo3MVP.retrofit;

import com.rxjavademo.Dagger2Demo3MVP.model.CryptoData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
   /* @GET("region/")
    Observable<List<CryptoData>> getData(@Query("africa") String limit);*/

    /*@GET("region/")
    Observable<List<CryptoData>> getData(@Query("africa") String limit);*/

    @GET("region/africa")
    Observable<List<CryptoData>> getData();
}
