package com.rxjavademo.roomdatabasedemo3.director;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rxjavademo.roomdatabasedemo3.db.Director;
import com.rxjavademo.roomdatabasedemo3.db.DirectorDao;
import com.rxjavademo.roomdatabasedemo3.db.MoviesDatabase;

import java.util.List;

public class DirectorsViewModel extends AndroidViewModel {

    private DirectorDao directorDao;
    private LiveData<List<Director>> directorsLiveData;

    public DirectorsViewModel(@NonNull Application application) {
        super(application);
        directorDao = MoviesDatabase.getDatabase(application).directorDao();
        directorsLiveData = directorDao.getAllDirectors();
    }

    public LiveData<List<Director>> getDirectorList() {
        return directorsLiveData;
    }

    public void insert(Director... directors) {
        directorDao.insert(directors);
    }

    public void update(Director director) {
        directorDao.update(director);
    }

    public void deleteAll() {
        directorDao.deleteAll();
    }

}
