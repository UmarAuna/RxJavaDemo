package com.rxjavademo.RXJavaDemo4.contract;

import com.rxjavademo.RXJavaDemo3.model.CropsItem;
import com.rxjavademo.RXJavaDemo4.callback.Callback;
import com.rxjavademo.RXJavaDemo4.model.CropsItems;
import com.rxjavademo.RXJavaDemo4.utils.UserTask;

import java.util.List;

public class UserPresenter implements UserContract.Presenter {
    UserContract.View mView;

     public UserPresenter(UserContract.View mView){
        this.mView = mView;
    }

    @Override
    public void loadUsers() {
        UserTask.getCrops(new Callback<List<CropsItems>>() {
            @Override
            public void returnResult(List<CropsItems> crops) {
                mView.loadDataInList(crops);
            }

            @Override
            public void returnError(String message) {
                mView.showError(message);
            }
        });
    }

    @Override
    public void start() {
        mView.init();
    }
}
