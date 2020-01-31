package com.cemerci.samplelogin.ui.register;

import com.cemerci.samplelogin.data.DataManager;
import com.cemerci.samplelogin.data.firebase.FirebaseHelper;
import com.cemerci.samplelogin.data.model.User;
import com.cemerci.samplelogin.ui.base.BaseViewModel;
import com.cemerci.samplelogin.utils.rx.SchedulerProvider;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
public class RegisterViewModel extends BaseViewModel<RegisterListener> implements FirebaseHelper.AddUserListener {

    public RegisterViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onPhotoClick() {
        getListener().takePhoto();
    }

    public void onRegisterClick() {
        getListener().register();
    }

    public void register(User user) {
        setIsLoading(true);
        getDataManager().addNewUser(user, this);
    }

    @Override
    public void onComplete() {
        setIsLoading(false);
        getListener().registerSuccess();
    }

    @Override
    public void onUserAlreadyExists() {
        setIsLoading(false);
        getListener().registerUserExists();
    }
}
