package com.rxjavademo.livedatademo1.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.rxjavademo.R;
import com.rxjavademo.livedatademo1.adapter.CountryAdapter;
import com.rxjavademo.livedatademo1.model.Country;
import com.rxjavademo.livedatademo1.viewmodel.CountryViewModel;

import java.util.List;

public class LiveDataDemo1 extends AppCompatActivity {
    //https://androidwave.com/working-with-livedata/
    //LifeCycle Awareness, ViewModel, LiveData, Retrofit, Glide, Lamda Expression

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    private CountryViewModel countryViewModel;

    CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_demo1);
        initializationViews();
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        getCountries();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCountries();
            }
        });
    }

    public void initializationViews(){
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        recyclerView = findViewById(R.id.blogRecyclerView);
    }

    public void getCountries(){
        swipeRefreshLayout.setRefreshing(true);
        countryViewModel.getAllCountry().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countryList) {
                swipeRefreshLayout.setRefreshing(false);
                prepareRecyclerView(countryList);
            }
        });
    }

    private void prepareRecyclerView(List<Country> countryList){
        countryAdapter = new CountryAdapter(countryList);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(countryAdapter);
        countryAdapter.notifyDataSetChanged();
    }
}
