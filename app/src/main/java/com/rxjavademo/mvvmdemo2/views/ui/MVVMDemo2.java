package com.rxjavademo.mvvmdemo2.views.ui;

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
import com.rxjavademo.mvvmdemo2.model.NewsArticle;
import com.rxjavademo.mvvmdemo2.model.NewsResponse;
import com.rxjavademo.mvvmdemo2.viewmodels.NewsViewModel;
import com.rxjavademo.mvvmdemo2.views.adapters.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MVVMDemo2 extends AppCompatActivity {
    //MVVM, LiveDat, Retrofit2
    //https://medium.com/@amtechnovation/android-architecture-component-mvvm-part-1-a2e7cff07a76
    //https://github.com/devamitkumartiwari/MVVMNews
    //https://newsapi.org/v2/top-headlines?country=ng&apiKey=b9a73a0b219f495995f00201dbfd426e

    ArrayList<NewsArticle> articleArrayList = new ArrayList<>();
    NewsAdapter newsAdapter;
    RecyclerView rvHeadline;
    NewsViewModel newsViewModel;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvmdemo2);
        rvHeadline = findViewById(R.id.rvNews);
        swipeRefreshLayout = findViewById(R.id.swiperefreshMVVM);
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.init();
        setupRecyclerView();
        getNews();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNews();
            }
        });

    }


    public void getNews(){
        swipeRefreshLayout.setRefreshing(true);
        newsViewModel.getNewsRepository().observe(this, new Observer<NewsResponse>() {
            @Override
            public void onChanged(NewsResponse newsResponse) {
                List<NewsArticle> newsArticles = newsResponse.getArticles();
                swipeRefreshLayout.setRefreshing(false);
                articleArrayList.addAll(newsArticles);
                newsAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setupRecyclerView(){
            newsAdapter = new NewsAdapter(MVVMDemo2.this, articleArrayList);
            if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                rvHeadline.setLayoutManager(new LinearLayoutManager(this));
            }else {
                rvHeadline.setLayoutManager(new GridLayoutManager(this, 2));
            }
            rvHeadline.setAdapter(newsAdapter);
            rvHeadline.setItemAnimator(new DefaultItemAnimator());
            rvHeadline.setNestedScrollingEnabled(true);
            newsAdapter.notifyDataSetChanged();
    }
}
