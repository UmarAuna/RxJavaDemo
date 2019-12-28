package com.rxjavademo.livedatademo1.networking;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL = "https://restcountries.eu/rest/v2/";

    private static Retrofit retrofit = null;
    public static RestApiService getApiService(){
        if(retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(RestApiService.class);
    }

}
