package com.rxjavademo.Dagger2Demo3MVP.mvp.contract;

import com.rxjavademo.Dagger2Demo3MVP.model.CryptoData;

import java.util.List;

public interface Dagger2Demo3MVPContract {
    interface View{
       void showData(List<CryptoData> data);
       void showError(String message);
       void showComplete();
       void showProgress();
       void hideProgress();
    }

    interface Presenter{
        void loadData();
    }


}
