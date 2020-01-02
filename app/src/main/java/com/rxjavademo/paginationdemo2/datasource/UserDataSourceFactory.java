package com.rxjavademo.paginationdemo2.datasource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


import com.rxjavademo.paginationdemo2.model.User;

import org.jetbrains.annotations.NotNull;

public class UserDataSourceFactory extends DataSource.Factory<Long, User> {

    public MutableLiveData<UserDataSource> userLiveDataSource=new MutableLiveData<>();
    @NotNull
    @Override public DataSource<Long, User> create() {
        UserDataSource userDataSource = new UserDataSource();
        userLiveDataSource.postValue(userDataSource);
        return userDataSource;
    }

}

