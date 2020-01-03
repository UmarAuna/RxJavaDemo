package com.rxjavademo.roomdatabasedemo3.movie;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rxjavademo.R;
import com.rxjavademo.roomdatabasedemo3.db.Movie;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMovies extends Fragment {

    private MoviesListAdapter moviesListAdapter;
    private MoviesViewModel moviesViewModel;
    private Context context;

    public static FragmentMovies newInstance() {
        // Required empty public constructor
        return new FragmentMovies();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        this.context = context;
        moviesListAdapter = new MoviesListAdapter(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_movies);
        recyclerView.setAdapter(moviesListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return  view;
    }

    private void initData() {
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getMoviesList().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                moviesListAdapter.setMovieList(movies);
            }
        });
    }

    public void removeData() {
        if (moviesListAdapter != null) {
            moviesViewModel.deleteAll();
        }
    }



}
