package com.rxjavademo.roomdatabasedemo3.director;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.rxjavademo.roomdatabasedemo3.db.Director;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDirectors extends Fragment {

    private DirectorsListAdapter directorsListAdapter;
    private DirectorsViewModel directorsViewModel;
    private Context context;

    public static FragmentDirectors newInstance() {
        // Required empty public constructor
        return new FragmentDirectors();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        this.context = context;
        directorsListAdapter = new DirectorsListAdapter(context);
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
        View view = inflater.inflate(R.layout.fragment_directors, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_directors);
        recyclerView.setAdapter(directorsListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    private void initData() {
        directorsViewModel = ViewModelProviders.of(this).get(DirectorsViewModel.class);
        directorsViewModel.getDirectorList().observe(this, new Observer<List<Director>>() {
            @Override
            public void onChanged(@Nullable List<Director> directors) {
                directorsListAdapter.setDirectorList(directors);
            }
        });
    }

    public void removeData() {
        if (directorsViewModel != null) {
            directorsViewModel.deleteAll();
        }
    }

}
