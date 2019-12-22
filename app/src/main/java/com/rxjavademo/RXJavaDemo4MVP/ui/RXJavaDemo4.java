package com.rxjavademo.RXJavaDemo4MVP.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.rxjavademo.R;
import com.rxjavademo.RXJavaDemo4MVP.adapter.CropedAdapter;
import com.rxjavademo.RXJavaDemo4MVP.contract.UserContract;
import com.rxjavademo.RXJavaDemo4MVP.contract.UserPresenter;
import com.rxjavademo.RXJavaDemo4MVP.model.CropsItems;

import java.util.List;

public class RXJavaDemo4 extends AppCompatActivity implements UserContract.View {
    //https://www.freecodecamp.org/news/how-to-set-up-networking-in-your-android-app-with-retrofit-rxjava-mvp-108e7153521a/
    private UserContract.Presenter mPresenter;
    private RecyclerView recyclerView;
    CropedAdapter cropedAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo4);
        mPresenter = new UserPresenter(this);
        mPresenter.start();
    }

    @Override
    public void init() {
        recyclerView = findViewById(R.id.crop_list_recycler_mvp);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        mPresenter.loadUsers();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadDataInList(List<CropsItems> cropsItems) {
        cropedAdapter = new CropedAdapter(cropsItems);
        recyclerView.setAdapter(cropedAdapter);
    }
}
