package com.rxjavademo.RXJavaDemo4MVP.networking.api;


import com.rxjavademo.RXJavaDemo4MVP.model.Crops;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CropsService {

    @GET("api/crop/?limit=34&offset=1")
    Observable<Crops> getCropsMVP(
            @Query("offset") long offset,
            @Query("limit") long   limit);
}
