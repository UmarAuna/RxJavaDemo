package com.rxjavademo.livedatademo1.networking;

import com.rxjavademo.livedatademo1.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService {
    @GET("region/africa")
    Call<List<Country>> getCountryList();
}
