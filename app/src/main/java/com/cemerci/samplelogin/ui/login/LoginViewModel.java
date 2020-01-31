package com.cemerci.samplelogin.ui.login;

import androidx.databinding.ObservableBoolean;

import com.cemerci.samplelogin.data.DataManager;
import com.cemerci.samplelogin.data.firebase.FirebaseHelper;
import com.cemerci.samplelogin.data.model.User;
import com.cemerci.samplelogin.ui.base.BaseViewModel;
import com.cemerci.samplelogin.utils.AppConstants;
import com.cemerci.samplelogin.utils.rx.SchedulerProvider;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
public class LoginViewModel extends BaseViewModel<LoginListener> implements FirebaseHelper.GetUserListener {

    public final ObservableBoolean rememberMe = new ObservableBoolean(true);

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onRegisterClick() {
        getListener().openRegisterActivity();
    }

    public void onLoginClick() {
        getListener().login();
    }

    void loginAction(String email, String password) {
        setIsLoading(true);
        getDataManager().getUser(email, this);
    }

    public void onRememberMeClick() {
        rememberMe.set(!rememberMe.get());
    }

    @Override
    public void onComplete(User user) {
        getDataManager().updateUserInfo(
                user,
                rememberMe.get() ? AppConstants.LoggedInMode.LOGGED_IN_MODE_SERVER
                        : AppConstants.LoggedInMode.LOGGED_IN_MODE_NO_SESSION
        );
        setIsLoading(false);
        getListener().openMainActivity();
    }

    @Override
    public void onNotFound() {
        setIsLoading(false);
        getListener().userNotFound();
    }
}
