package com.rxjavademo.RXJavaDemo3.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.rxjavademo.R;
import com.rxjavademo.RXJavaDemo3.model.CropsItem;
import com.rxjavademo.RXJavaDemo3.model.Results;
import com.rxjavademo.RXJavaDemo3.service.APIClient;
import com.rxjavademo.RXJavaDemo3.service.APIService;
import com.rxjavademo.RXJavaDemo3.utils.CropsAdapter;

import org.reactivestreams.Subscriber;

import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RXJavaDemo3 extends AppCompatActivity {
    RecyclerView cropListRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo3);
        cropListRecycler = findViewById(R.id.crop_list_recycler);

        cropListRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        cropListRecycler.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        cropListRecycler.addItemDecoration(itemDecoration);
        loadCrops();

    }

    public void loadCrops(){
        APIService apiService = APIClient.getCacheEnabledRetrofit(getApplicationContext()).create(APIService.class);
        //Observable <Results>  crops = apiService.getCrops(1,35);
        apiService.getCrops(1,35)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Results>() {
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
                });

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
}
