package com.cemerci.samplelogin.data;

import com.cemerci.samplelogin.data.firebase.FirebaseHelper;
import com.cemerci.samplelogin.data.model.User;
import com.cemerci.samplelogin.data.prefs.PreferencesHelper;
import com.cemerci.samplelogin.utils.AppConstants.LoggedInMode;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
public interface DataManager extends PreferencesHelper, FirebaseHelper {
    void updateUserInfo(
            User user,
            LoggedInMode loggedInMode
    );

    void loggedOut();
    void addNewUser(User user, AddUserListener addUserListener);
}
