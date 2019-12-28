package com.rxjavademo.databindingdemo3.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.rxjavademo.databindingdemo3.model.MoviesItem;
import com.rxjavademo.databindingdemo3.model.MoviesResponse;
import com.rxjavademo.databindingdemo3.networking.MoviesDataService;
import com.rxjavademo.databindingdemo3.networking.RetrofitClient;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    //Let’s create an employee data Repository, that deal with the network and provide MutableLiveData instance.
    private static final String TAG = "MovieRepository";
    private ArrayList<MoviesItem> moviesItems = new ArrayList<>();
    private MutableLiveData<List<MoviesItem>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application){
        this.application = application;
    }




    public MutableLiveData<List<MoviesItem>> getMutableLiveData(){
        final MoviesDataService moviesDataService = RetrofitClient.getService();

        Call<MoviesResponse> call = moviesDataService.getMovies();
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NotNull Call<MoviesResponse> call, @NotNull Response<MoviesResponse> response) {
                MoviesResponse moviesResponse = response.body();
                if(moviesResponse !=null & moviesResponse.getMovies() !=null ){
                    moviesItems = (ArrayList<MoviesItem>) moviesResponse.getMovies();
                    mutableLiveData.setValue(moviesItems);
                }
            }

            @Override
            public void onFailure(@NotNull Call<MoviesResponse> call, @NotNull Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
