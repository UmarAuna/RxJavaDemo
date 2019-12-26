package com.rxjavademo.databindingdemo3.networking;

import com.rxjavademo.databindingdemo3.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesDataService {
    @GET("movies")
    Call<MoviesResponse> getMovies();
}
