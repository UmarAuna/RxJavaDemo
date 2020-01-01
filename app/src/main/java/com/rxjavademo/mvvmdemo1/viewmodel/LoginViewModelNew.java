package com.rxjavademo.mvvmdemo1.viewmodel;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rxjavademo.mvvmdemo1.model.OurUser;

public class LoginViewModelNew extends ViewModel {

    //The class now extends ViewModel since we no longer need BaseObservable
    //Now, we’ve changed the ObservableFields to MutableLiveData.
    // Changes in the MutableLiveData would be automatically updated in the layout thanks to Data Binding.

    public MutableLiveData<String> errorPassword = new MutableLiveData<>();
    public MutableLiveData<String> errorEmail = new MutableLiveData<>();

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<Integer> busy;

    public MutableLiveData<Integer>getBusy(){
        if (busy == null){
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }
        return busy;
    }


    public LoginViewModelNew(){
    }

    private MutableLiveData<OurUser> userMutableLiveData;

    public LiveData<OurUser> getUser(){
        if(userMutableLiveData == null){
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    /*
     * If this is confusing to you, just imagine back in your high school years. You were observing your crush from a corner of the class.
     * You would know every information about her like her weights, heights, and hair styles. You would also update that information if it has changed.
     * But in her side, she has no idea that you were secretly observing her and you are probably not the only one who was observing.
     * One more thing to add is you should only observe one crush at a time ( I assume you’re not that pretty
     *  boy in high school who managed to hangout with 3 girls at the same time ).
     *
     * Guess what! You’re the view and your crush-back-then is the view model.
     *
     *
     * You can’t observe your crush if she disappears or refuses to share her information publicly, in other words, she’s not observable.
     * */

    public void onLoginClicked(){
        getBusy().setValue(0); // View.VISIBLE
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                OurUser user = new OurUser(email.getValue(), password.getValue());

                if(!user.isEmailValid()){
                    errorEmail.setValue("Enter a valid email address");
                }else{
                    errorEmail.setValue(null);
                }

                if(!user.isPasswordLengthGreaterThan5()){
                    errorPassword.setValue("Password Length should be greater than 5");
                }else{
                    errorPassword.setValue(null);
                }

                userMutableLiveData.setValue(user);
                busy.setValue(8); //8 == View.GONE
            }
        }, 3000);
    }
}
