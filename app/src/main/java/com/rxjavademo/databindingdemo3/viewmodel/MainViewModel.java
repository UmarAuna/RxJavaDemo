package com.rxjavademo.databindingdemo3.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rxjavademo.databindingdemo3.model.MoviesItem;
import com.rxjavademo.databindingdemo3.repository.MovieRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository();
    }

    public LiveData<List<MoviesItem>> getAllEmployee(){
        return movieRepository.getMutableLiveData();
    }
}
