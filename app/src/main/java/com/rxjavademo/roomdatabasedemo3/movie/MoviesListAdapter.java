package com.rxjavademo.roomdatabasedemo3.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.rxjavademo.R;
import com.rxjavademo.roomdatabasedemo3.db.Director;
import com.rxjavademo.roomdatabasedemo3.db.Movie;
import com.rxjavademo.roomdatabasedemo3.db.MoviesDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.rxjavademo.roomdatabasedemo3.movie.MovieSaveDialogFragment.TAG_DIALOG_MOVIE_SAVE;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Movie> movieList;
    private Context context;

    public MoviesListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        final View itemView = layoutInflater.inflate(R.layout.item_list_movie_room3, parent, false);
        return new MoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull MoviesViewHolder holder, int position) {
        if (movieList == null) {
            return;
        }

        final Movie movie = movieList.get(position);
        if (movie != null) {
            holder.titleText.setText(movie.title);

            final Director director = MoviesDatabase.getDatabase(context).directorDao().findDirectorById(movie.directorId);
            final String directorFullName;
            if (director != null) {
                holder.directorText.setText(director.fullName);
                directorFullName = director.fullName;
            } else {
                directorFullName = "";
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment dialogFragment = MovieSaveDialogFragment.newInstance(movie.title, directorFullName);
                    dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), TAG_DIALOG_MOVIE_SAVE);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (movieList == null) {
            return 0;
        } else {
            return movieList.size();
        }
    }

    static class MoviesViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText;
        private TextView directorText;

        public MoviesViewHolder(View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.tvMovieTitle);
            directorText = itemView.findViewById(R.id.tvMovieDirectorFullName);
        }
    }
}
