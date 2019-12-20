package com.rxjavademo.RXJavaDemo4.contract;

import com.rxjavademo.RXJavaDemo3.model.CropsItem;
import com.rxjavademo.RXJavaDemo4.model.CropsItems;

import java.util.List;

public interface UserContract {

    interface View{
        void init();

        void showError(String message);

        void loadDataInList(List<CropsItems> users);
    }

    //The only real task of the presenter is to get the data from the API and send it to the View
    interface Presenter{

        void start();

        void loadUsers();
    }
}
