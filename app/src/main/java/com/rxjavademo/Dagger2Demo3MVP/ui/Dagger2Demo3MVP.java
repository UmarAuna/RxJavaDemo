package com.rxjavademo.Dagger2Demo3MVP.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rxjavademo.Dagger2Demo3MVP.adapter.RecyclerViewAdapter;
import com.rxjavademo.Dagger2Demo3MVP.di.component.ApplicationComponent;
import com.rxjavademo.Dagger2Demo3MVP.di.component.Dagger2Demo3MVPComponent;
import com.rxjavademo.Dagger2Demo3MVP.di.component.DaggerDagger2Demo3MVPComponent;
import com.rxjavademo.Dagger2Demo3MVP.di.module.Dagger2Demo3MVPContextModule;
import com.rxjavademo.Dagger2Demo3MVP.di.module.Dagger2Demo3MVP_MvpModule;
import com.rxjavademo.Dagger2Demo3MVP.di.qualifier.ApplicationContext;
import com.rxjavademo.Dagger2Demo3MVP.model.CryptoData;
import com.rxjavademo.Dagger2Demo3MVP.mvp.contract.Dagger2Demo3MVPContract;
import com.rxjavademo.Dagger2Demo3MVP.mvp.presenter.PresenterImpl;
import com.rxjavademo.Dagger2Demo3MVP.util.MyApplication;
import com.rxjavademo.R;

import java.util.List;

import javax.inject.Inject;

public class Dagger2Demo3MVP extends AppCompatActivity implements Dagger2Demo3MVPContract.View, RecyclerViewAdapter.ClickListener {
    //https://www.journaldev.com/20654/android-mvp-dagger2-retrofit-rxjava
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    Dagger2Demo3MVPComponent dagger2Demo3MVPComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ApplicationContext
    public Context activityContext;

    @Inject
    PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2_demo3_mvp);
        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        //Rebuild project here
        dagger2Demo3MVPComponent = DaggerDagger2Demo3MVPComponent.builder()
                .dagger2Demo3MVPContextModule(new Dagger2Demo3MVPContextModule(this))
                .dagger2Demo3MVP_MvpModule(new Dagger2Demo3MVP_MvpModule(this))
                .applicationComponent(applicationComponent)
                .build();

        dagger2Demo3MVPComponent.injectDagger2Demo3MVP(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        recyclerView.setAdapter(recyclerViewAdapter);
        progressBar = findViewById(R.id.progressBar);

        presenter.loadData();

    }

    @Override
    public void launchIntent(String name) {
        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
        // startActivity(new Intent(activityContext, DetailActivity.class).putExtra("name", name));
    }

    @Override
    public void showData(List<CryptoData> data) {
        recyclerViewAdapter.setData(data);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
