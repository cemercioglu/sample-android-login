package com.cemerci.samplelogin.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.cemerci.samplelogin.data.model.User;
import com.cemerci.samplelogin.utils.AppConstants;
import com.google.gson.Gson;

import javax.inject.Inject;

import static com.cemerci.samplelogin.utils.AppConstants.APP_PREF_NAME;
import static com.cemerci.samplelogin.utils.AppConstants.LoggedInMode;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
public class AppPreferencesHelper implements PreferencesHelper {

    private final SharedPreferences mPreferences;

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_USER_INFO = "PREF_KEY_USER_INFO";

    @Inject
    Gson gson;

    @Inject
    AppPreferencesHelper(Context context) {
        mPreferences = context.getSharedPreferences(APP_PREF_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public LoggedInMode getUserMode() {
        int modeValue = mPreferences.getInt(PREF_KEY_USER_LOGGED_IN_MODE, LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
        return modeValue == LoggedInMode.LOGGED_IN_MODE_SERVER.getType() ? LoggedInMode.LOGGED_IN_MODE_SERVER : LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT;
    }

    @Override
    public void setUserLoggedInMode(AppConstants.LoggedInMode mode) {
        mPreferences.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public User getUserInfo() {
        String userJson = mPreferences.getString(PREF_KEY_USER_INFO, null);
        User user = gson.fromJson(userJson, User.class);
        return user;
    }

    @Override
    public void setUserInfo(User user) {
        String jsonLogonModel = gson.toJson(user);
        mPreferences.edit().putString(PREF_KEY_USER_INFO, jsonLogonModel).apply();
    }


}
