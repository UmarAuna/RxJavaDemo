package com.rxjavademo.RXJavaDemo3.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.rxjavademo.R;
import com.rxjavademo.RXJavaDemo3.model.CropsItem;
import com.rxjavademo.RXJavaDemo3.model.Jokes;
import com.rxjavademo.RXJavaDemo3.model.Results;
import com.rxjavademo.RXJavaDemo3.service.APIClient;
import com.rxjavademo.RXJavaDemo3.service.APIService;
import com.rxjavademo.RXJavaDemo3.utils.CropsAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RXJavaDemo3 extends AppCompatActivity {
    //RXjava 2, Retrofit
    private Disposable disposable, disposable2 = null;
    RecyclerView cropListRecycler;
    ProgressDialog progressDialog;
    APIService apiService, newapiService;
    TextView jokesText, jokesText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo3);
        cropListRecycler = findViewById(R.id.crop_list_recycler);
        jokesText = findViewById(R.id.jokes);
        jokesText2 = findViewById(R.id.jokes2);
        progressDialog = new ProgressDialog(this);
        cropListRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        cropListRecycler.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        cropListRecycler.addItemDecoration(itemDecoration);
        loadCrops();

    }

    public void loadCrops(){

        apiService = APIClient.getCacheEnabledRetrofit(getApplicationContext()).create(APIService.class);

        newapiService =  APIClient.getCacheEnabledRetrofit2(getApplicationContext()).create(APIService.class);

        //Call <Results>  crops = apiService.getCrops(1,35);


       /* apiService.getCrops(1,35)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                }).subscribe(getDisposableObserver());*/


        disposable = Observable.interval(1000, 10000, TimeUnit.MILLISECONDS)
                .doOnError(i -> isOnline(getApplicationContext()))
                //.debounce(500, TimeUnit.MILLISECONDS) // Avoid getting spammed with key stroke changes
                .onErrorResumeNext(Observable.empty())// <-- This will terminate upstream (ie. we will stop receiving text view changes after an error!)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                }).subscribe(this::callCrops);


        disposable2 = Observable.interval(1000, 10000, TimeUnit.MILLISECONDS)
                .doOnError(i -> isOnline(getApplicationContext()))
                //.debounce(500, TimeUnit.MILLISECONDS) // Avoid getting spammed with key stroke changes
                .onErrorResumeNext(Observable.empty())// <-- This will terminate upstream (ie. we will stop receiving text view changes after an error!)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                }).subscribe(this::callJokes);



                /*.subscribe(new DisposableObserver<Results>() {
                    @Override
                    public void onNext(Results results) {
                        List<CropsItem> list_crop =results.getResults();
                        cropListRecycler.setAdapter(new CropsAdapter(getApplicationContext(),list_crop));
                        cropListRecycler.smoothScrollToPosition(0);
                        Log.d("DISPLAY", results.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "Error Fetching Data! " + e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });*/

        /*crops.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(@NonNull Call<Results> call, @NonNull Response<Results> response) {
                List<CropsItem> list_crop =response.body().getResults();
                cropListRecycler.setAdapter(new CropsAdapter(getApplicationContext(),list_crop));
                cropListRecycler.smoothScrollToPosition(0);
                Log.d("DISPLAY", response.body().toString());


            }

            @Override
            public void onFailure(@NonNull Call<Results> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Error Fetching Data!", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private void callJokes(Long along){

        Observable<Jokes> observable = newapiService.getRandomJoke("random");
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getJokesDisposableObserver());
    }

    private void callCrops(Long along){

        Observable<Results> observable = apiService.getCrops(1,35);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getDisposableObserver());
    }



    private DisposableObserver <Jokes> getJokesDisposableObserver(){
        return new DisposableObserver<Jokes>() {
            @Override
            public void onNext(Jokes jokes) {
                if(!TextUtils.isEmpty(jokes.getSetup())){
                    jokesText.setText("Joke: "+jokes.getSetup());
                    jokesText2.setText("Answer "+ jokes.getPunchline());
                    Log.d("JOKES", jokes.toString());
                }
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error Fetching Data! " + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(getApplicationContext(), "Completed Jokes", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        };
    }

    private DisposableObserver <Results> getDisposableObserver(){

        return new DisposableObserver<Results>() {
            @Override
            public void onNext(Results results) {
                List<CropsItem> list_crop =results.getResults();
                cropListRecycler.setAdapter(new CropsAdapter(getApplicationContext(),list_crop));
                Log.d("CROP", results.toString());
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error Fetching Data! " + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(getApplicationContext(), "Completed Crop", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (disposable.isDisposed()) {
            disposable = Observable.interval(1000, 5000, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::callCrops);
        }else if(disposable2.isDisposed()){
            disposable2 = Observable.interval(1000, 5000, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::callCrops);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }else if(disposable2 != null && !disposable2.isDisposed()){
            disposable2.dispose();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }else if(disposable2 != null && !disposable2.isDisposed()){
            disposable2.dispose();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }else if(disposable2 != null && !disposable2.isDisposed()){
            disposable2.dispose();
        }
    }

    public boolean isOnline(Context contextValue) {
        ConnectivityManager cm = (ConnectivityManager)
                contextValue.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
