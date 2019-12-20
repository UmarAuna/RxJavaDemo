package com.rxjavademo.RXJavaDemo4.utils;

import com.rxjavademo.RXJavaDemo4.callback.Callback;

import com.rxjavademo.RXJavaDemo4.model.Crops;
import com.rxjavademo.RXJavaDemo4.model.CropsItems;
import com.rxjavademo.RXJavaDemo4.networking.utils.NetworkingUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserTask {
    //This class will be responsible to make API calls and return the result to the presenter which will show the result or the error depending upon what we get.

    public static void getCrops(final Callback<List<CropsItems>> callback) {
        NetworkingUtils.getCropsApiInstance()
                .getCropsMVP(1,35)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Crops>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Crops crops) {
                        callback.returnResult(crops.getResults());
                    }


                    @Override
                    public void onError(Throwable e) {
                        callback.returnError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
