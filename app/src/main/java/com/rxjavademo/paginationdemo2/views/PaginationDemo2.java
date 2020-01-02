package com.rxjavademo.paginationdemo2.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rxjavademo.R;
import com.rxjavademo.paginationdemo2.model.User;
import com.rxjavademo.paginationdemo2.viewmodel.UserViewModel;

public class PaginationDemo2 extends AppCompatActivity {
    //https://androidwave.com/android-paging-library/
    // ViewModel, LiveData, Paging,Retrofit
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination_demo2);
        recyclerView = findViewById(R.id.recyclerView);

        final UserAdapter adapter = new UserAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        UserViewModel itemViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        itemViewModel.userPagedList.observe(this, new Observer<PagedList<User>>() {
            @Override public void onChanged(PagedList<User> users) {
                adapter.submitList(users);
            }
        });
        recyclerView.setAdapter(adapter);

    }
}
