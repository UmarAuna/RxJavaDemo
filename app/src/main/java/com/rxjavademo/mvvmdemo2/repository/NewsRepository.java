package com.rxjavademo.mvvmdemo2.repository;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.rxjavademo.mvvmdemo2.model.NewsResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private static NewsRepository newsRepository;

    public static NewsRepository getInstance(){
        if(newsRepository == null){
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private NewsApi newsApi;

    public NewsRepository(){
        newsApi = RetrofitService.createService(NewsApi.class);
    }

    public MutableLiveData<NewsResponse> getNews(String source, String key){
        MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();

        newsApi.getNewsList(source, key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NotNull Call<NewsResponse> call, @NotNull Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<NewsResponse> call, @NotNull Throwable t) {
                Log.d("ERROR", "No Internet connection or invalid parameter");
            }
        });
        return newsData;
    }
}
