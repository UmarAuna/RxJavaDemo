package com.rxjavademo.roomdatabasedemo3.movie;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rxjavademo.roomdatabasedemo3.db.Movie;
import com.rxjavademo.roomdatabasedemo3.db.MovieDao;
import com.rxjavademo.roomdatabasedemo3.db.MoviesDatabase;

import java.util.List;

public class MoviesViewModel extends AndroidViewModel {
    private MovieDao movieDao;
    private LiveData<List<Movie>> moviesLiveData;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        movieDao = MoviesDatabase.getDatabase(application).movieDao();
        moviesLiveData = movieDao.getAllMovies();
    }

    public LiveData<List<Movie>> getMoviesList() {
        return moviesLiveData;
    }

    public void insert(Movie... movies) {
        movieDao.insert(movies);
    }

    public void update(Movie movie) {
        movieDao.update(movie);
    }

    public void deleteAll() {
        movieDao.deleteAll();
    }
}
