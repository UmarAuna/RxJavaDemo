package com.rxjavademo.Dagger2Demo3MVP.mvp.presenter;

import com.rxjavademo.Dagger2Demo3MVP.model.CryptoData;
import com.rxjavademo.Dagger2Demo3MVP.mvp.contract.Dagger2Demo3MVPContract;
import com.rxjavademo.Dagger2Demo3MVP.retrofit.APIInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PresenterImpl implements Dagger2Demo3MVPContract.Presenter {
    APIInterface apiInterface;
    Dagger2Demo3MVPContract.View mView;

    //@Inject on the constructor says that this class’s object would be injected in the MainActivity.
    //The Presenter invokes the required View interface’s methods that’ll trigger the actions in the MainActivity.
    @Inject
    public PresenterImpl(APIInterface apiInterface, Dagger2Demo3MVPContract.View mView){
        this.apiInterface = apiInterface;
        this.mView = mView;
    }


    @Override
    public void loadData() {
        mView.showProgress();

        apiInterface.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CryptoData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<CryptoData> cryptoData) {
                        mView.showData(cryptoData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError("Error occurred");
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.showComplete();
                        mView.hideProgress();
                    }
                });
    }
}
