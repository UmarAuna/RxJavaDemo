package com.rxjavademo.mvvmdemo2.repository;

import com.rxjavademo.mvvmdemo2.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("top-headlines")
    Call<NewsResponse> getNewsList(@Query("country") String newsSource,
                                   @Query("apiKey") String apiKey);
}
