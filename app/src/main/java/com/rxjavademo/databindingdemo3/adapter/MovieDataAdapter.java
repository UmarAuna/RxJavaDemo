package com.rxjavademo.databindingdemo3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rxjavademo.R;
import com.rxjavademo.databinding.ItemMoviesListBinding;
import com.rxjavademo.databindingdemo3.model.MoviesItem;

import java.util.ArrayList;

public class MovieDataAdapter extends RecyclerView.Adapter<MovieDataAdapter.MovieViewHolder> {

    private ArrayList<MoviesItem> movies;

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMoviesListBinding itemMoviesListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movies_list, parent,false);
        return new MovieViewHolder(itemMoviesListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MoviesItem moviesItem = movies.get(position);
        holder.itemMoviesListBinding.setMovies(moviesItem);
        holder.itemMoviesListBinding.setPics(moviesItem.getUrl());
    }


    @Override
    public int getItemCount() {
        if (movies != null) {
            return  movies.size();
        }else{
            return 0;
        }
    }

    public void setMoviesList(ArrayList<MoviesItem> movies){
        this.movies = movies;
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ItemMoviesListBinding itemMoviesListBinding;

        public MovieViewHolder(@NonNull ItemMoviesListBinding itemMoviesListBinding) {
            super(itemMoviesListBinding.getRoot());

            this.itemMoviesListBinding = itemMoviesListBinding;
        }
    }
}


