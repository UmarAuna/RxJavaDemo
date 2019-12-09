package com.rxjavademo.RXJavaDemo3.service;

import com.rxjavademo.RXJavaDemo3.model.Jokes;
import com.rxjavademo.RXJavaDemo3.model.Results;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    /*@GET("api/crop/?limit=34&offset=1")
    Call<Results> getCrops(
            @Query("offset") long offset,
            @Query("limit") long   limit);*/

    @GET("api/crop/?limit=34&offset=1")
    Observable<Results> getCrops(
            @Query("offset") long offset,
            @Query("limit") long   limit);

    @GET("{path}")
    Observable<Jokes> getRandomJoke(
            @Path("path") String path);
}
