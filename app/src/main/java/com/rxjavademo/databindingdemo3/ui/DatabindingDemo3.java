package com.rxjavademo.databindingdemo3.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.Callback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rxjavademo.R;
import com.rxjavademo.databinding.ActivityDatabindingDemo3Binding;
import com.rxjavademo.databindingdemo3.adapter.MovieDataAdapter;
import com.rxjavademo.databindingdemo3.model.MoviesItem;
import com.rxjavademo.databindingdemo3.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class DatabindingDemo3 extends AppCompatActivity {
    //Databinding, LiveData, Retrofit, Glide
    //https://androidwave.com/android-data-binding-recyclerview/

    private MainViewModel mainViewModel;
    private MovieDataAdapter movieDataAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabindingDemo3Binding activityDatabindingDemo3Binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_demo3);

        recyclerView = activityDatabindingDemo3Binding.viewEmployees;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        movieDataAdapter = new MovieDataAdapter();
        recyclerView.setAdapter(movieDataAdapter);
        getAllEmployee();
    }

    private void getAllEmployee(){
        mainViewModel.getAllEmployee().observe(this, new Observer<List<MoviesItem>>() {
            @Override
            public void onChanged(List<MoviesItem> moviesItems) {
                movieDataAdapter.setMoviesList((ArrayList<MoviesItem>) moviesItems);
            }
        });
    }

}
