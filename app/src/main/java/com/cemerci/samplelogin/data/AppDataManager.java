package com.cemerci.samplelogin.data;

import android.content.Context;

import com.cemerci.samplelogin.data.firebase.FirebaseHelper;
import com.cemerci.samplelogin.data.model.User;
import com.cemerci.samplelogin.data.prefs.PreferencesHelper;
import com.cemerci.samplelogin.utils.AppConstants.LoggedInMode;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
@Singleton
public class AppDataManager implements DataManager {
    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final FirebaseHelper mFirebaseHelper;

    @Inject
    public AppDataManager(Context context, PreferencesHelper preferencesHelper, FirebaseHelper firebaseHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mFirebaseHelper = firebaseHelper;

    }

    @Override
    public LoggedInMode getUserMode() {
        return mPreferencesHelper.getUserMode();
    }

    @Override
    public void setUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setUserLoggedInMode(mode);
    }

    @Override
    public User getUserInfo() {
        return mPreferencesHelper.getUserInfo();
    }

    @Override
    public void setUserInfo(User user) {
        mPreferencesHelper.setUserInfo(user);
    }


    @Override
    public void addNewUserToFb(User user, AddUserListener addUserListener) {
        mFirebaseHelper.addNewUserToFb(user, addUserListener);
    }

    @Override
    public void getUser(String email, GetUserListener getUserListener) {
        mFirebaseHelper.getUser(email, getUserListener);
    }

    @Override
    public void updateUserFb(User user, UpdateUserListener updateUserListener) {
        mFirebaseHelper.updateUserFb(user, updateUserListener);
    }

    @Override
    public void updateUserInfo(User user, LoggedInMode loggedInMode) {
        mPreferencesHelper.setUserLoggedInMode(loggedInMode);
        mPreferencesHelper.setUserInfo(user);
    }

    @Override
    public void loggedOut() {
        updateUserInfo(null,
                LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
    }

    @Override
    public void addNewUser(User user, AddUserListener addUserListener) {
        addNewUserToFb(user, addUserListener);
    }


}
