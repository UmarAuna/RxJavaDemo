package com.rxjavademo.paginationdemo2.networking;

import com.rxjavademo.paginationdemo2.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("users")
    Call<UserResponse> getUsers(@Query("page") long page);

}
