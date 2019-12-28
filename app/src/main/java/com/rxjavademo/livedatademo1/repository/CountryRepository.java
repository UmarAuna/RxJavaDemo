package com.rxjavademo.livedatademo1.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.rxjavademo.livedatademo1.model.Country;
import com.rxjavademo.livedatademo1.networking.RestApiService;
import com.rxjavademo.livedatademo1.networking.RetrofitInstance;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryRepository {
    private MutableLiveData<List<Country>> mutableLiveData = new MutableLiveData<>();
    private Application application;
    private List<Country> countryList;

    public CountryRepository(Application application){
        this.application = application;
    }

    public MutableLiveData<List<Country>> getMutableLiveData(){
        RestApiService apiService = RetrofitInstance.getApiService();

        Call<List<Country>> call = apiService.getCountryList();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(@NotNull Call<List<Country>> call, @NotNull Response<List<Country>> response) {

                countryList = response.body();
                mutableLiveData.setValue(countryList);
            }

            @Override
            public void onFailure(@NotNull Call<List<Country>> call, @NotNull Throwable t) {
                Log.d("TAG", "" + t );
            }
        });


        return mutableLiveData;
    }
}
