package com.rxjavademo.roomdatabasedemo3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rxjavademo.R;
import com.rxjavademo.roomdatabasedemo3.db.MoviesDatabase;
import com.rxjavademo.roomdatabasedemo3.director.DirectorSaveDialogFragment;
import com.rxjavademo.roomdatabasedemo3.director.FragmentDirectors;
import com.rxjavademo.roomdatabasedemo3.movie.FragmentMovies;
import com.rxjavademo.roomdatabasedemo3.movie.MovieSaveDialogFragment;

import static com.rxjavademo.roomdatabasedemo3.director.DirectorSaveDialogFragment.TAG_DIALOG_DIRECTOR_SAVE;
import static com.rxjavademo.roomdatabasedemo3.movie.MovieSaveDialogFragment.TAG_DIALOG_MOVIE_SAVE;

public class RoomDatabaseDemo3 extends AppCompatActivity {
    //https://medium.com/@tonia.tkachuk/android-app-example-using-room-database-63f7091e69af
    //https://github.com/lomza/movies-room
    private boolean MOVIES_SHOWN = true;
    private Fragment shownFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database_demo3);

        setToolbar(getString(R.string.app_name));
        initView();

        if (savedInstanceState == null) {
            showFragment(FragmentMovies.newInstance());
        }
    }

    public void setToolbar(@NonNull String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }

    private void initView() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_movies:
                        MOVIES_SHOWN = true;
                        showFragment(FragmentMovies.newInstance());
                        return true;
                    case R.id.navigation_directors:
                        MOVIES_SHOWN = false;
                        showFragment(FragmentDirectors.newInstance());
                        return true;
                }
                return false;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSaveDialog();
            }
        });
    }

    private void showFragment(final Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHolder, fragment);
        fragmentTransaction.commitNow();
        shownFragment = fragment;
    }

    private void showSaveDialog() {
        DialogFragment dialogFragment;
        String tag;
        if (MOVIES_SHOWN) {
            dialogFragment = MovieSaveDialogFragment.newInstance(null, null);
            tag = TAG_DIALOG_MOVIE_SAVE;
        } else {
            dialogFragment = DirectorSaveDialogFragment.newInstance(null);
            tag = TAG_DIALOG_DIRECTOR_SAVE;
        }

        dialogFragment.show(getSupportFragmentManager(), tag);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == R.id.action_delete_list_data) {
            deleteCurrentListData();
            return true;
        } else if (id == R.id.action_re_create_database) {
            reCreateDatabase();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteCurrentListData() {
        if (MOVIES_SHOWN) {
            ((FragmentMovies) shownFragment).removeData();
        } else {
            ((FragmentDirectors) shownFragment).removeData();
        }
    }

    private void reCreateDatabase() {
        MoviesDatabase.getDatabase(this).clearDb();
    }


}
