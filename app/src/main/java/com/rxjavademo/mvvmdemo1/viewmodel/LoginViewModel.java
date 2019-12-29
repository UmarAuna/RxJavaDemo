package com.rxjavademo.mvvmdemo1.viewmodel;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.rxjavademo.databindingdemo1.User;
import com.rxjavademo.mvvmdemo1.model.OurUser;

public class LoginViewModel extends BaseObservable {
    //The above class can also extend ViewModel.
    // But we need BaseObservable since it converts the data into streams and notifies when the toastMessage property is changed.
    private OurUser user;

    private String sucessMessage = "Login was sucessful";
    private String errorMessage = "Email or Password not valid";

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
    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }
    //So in the above application, the ViewModel updates the Model by listening to the changes in the View.
    //Also, the Model can update the view via the ViewModel using the notifyPropertyChanged
    public void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public void setUserEmail(String email){
        user.setEmail(email);
        notifyPropertyChanged(BR.userEmail);
    }

    @Bindable
    public String getUserEmail(){
        return user.getEmail();
    }

    @Bindable
    public String getUserPassword() {
        return user.getPassword();
    }

    public void setUserPassword(String password){
        user.setPassword(password);
        notifyPropertyChanged(BR.userPassword);
    }

    public LoginViewModel(){
        user = new OurUser("","" );
    }

    public void onLoginClicked(){
        if (isInputDataValid()){
            setToastMessage(sucessMessage);
        }else{
            setToastMessage(errorMessage);
        }
    }

    public boolean isInputDataValid(){
        return !TextUtils.isEmpty(getUserEmail()) && Patterns.EMAIL_ADDRESS.matcher(getUserEmail()).matches() && getUserPassword().length() > 5;
    }




}
