package com.rxjavademo.mvvmdemo1.viewmodel;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rxjavademo.databindingdemo1.User;
import com.rxjavademo.mvvmdemo1.model.OurUser;

public class LoginViewModelOld extends BaseObservable {
    private String email, password;
    private int busy = 8;

    //An ObservableField is an object wrapper to make it observable.
    //ObservableField isn’t lifecycle aware.
    public final ObservableField<String> errorPassword = new ObservableField<>();
    public final ObservableField<String> errorEmail = new ObservableField<>();


    public LoginViewModelOld(){
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

    @Bindable
    @NonNull
    public String getEmail(){
        return this.email;
    }

    //So in the above application, the ViewModel updates the Model by listening to the changes in the View.
    //Also, the Model can update the view via the ViewModel using the notifyPropertyChanged
    public void setEmail(@NonNull String email){
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    @NonNull
    public String getPassword() {
        return this.password;
    }

    public void setPassword(@NonNull String password){
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public int getBusy() {
        return this.busy;
    }

    private void setBusy(int busy) {
        this.busy = busy;
        notifyPropertyChanged(BR.busy);
    }


    public void onLoginClicked(){
        setBusy(0); // View
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                OurUser user = new OurUser(getEmail(), getPassword());

                if(!user.isEmailValid()){
                    errorEmail.set("Enter a valid email address");
                }else{
                    errorEmail.set(null);
                }

                if(!user.isPasswordLengthGreaterThan5()){
                    errorPassword.set("Password Length should be greater than 5");
                }else{
                    errorPassword.set(null);
                }

                userMutableLiveData.setValue(user);
                setBusy(8); //8 == View.GONE
            }
        }, 5000);
    }

}
