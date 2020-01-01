package com.rxjavademo.mvvmdemo2.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rxjavademo.BuildConfig;
import com.rxjavademo.mvvmdemo2.model.NewsResponse;
import com.rxjavademo.mvvmdemo2.repository.NewsRepository;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<NewsResponse> mutableLiveData;
    private NewsRepository newsRepository;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        newsRepository = NewsRepository.getInstance();
        try {
            if(BuildConfig.NEWS_KEY.isEmpty()){
                Log.d("NEWS_KEY_ERROR", "Key is empty");
            }
            mutableLiveData = newsRepository.getNews("ng", BuildConfig.NEWS_KEY);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public LiveData<NewsResponse> getNewsRepository(){
        return mutableLiveData;
    }

}
