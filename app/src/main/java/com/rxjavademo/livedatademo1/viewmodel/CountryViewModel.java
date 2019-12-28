package com.rxjavademo.livedatademo1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rxjavademo.livedatademo1.model.Country;
import com.rxjavademo.livedatademo1.repository.CountryRepository;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {
    private CountryRepository countryRepository;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepository = new CountryRepository(application);
    }

    public LiveData<List<Country>> getAllCountry(){
        return countryRepository.getMutableLiveData();
    }

}
