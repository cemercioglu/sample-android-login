package com.cemerci.samplelogin.data.prefs;

import com.cemerci.samplelogin.data.model.User;
import com.cemerci.samplelogin.utils.AppConstants.LoggedInMode;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
public interface PreferencesHelper {

    LoggedInMode getUserMode();

    void setUserLoggedInMode(LoggedInMode mode);

    User getUserInfo();

    void setUserInfo(User user);
}
