package com.cemerci.samplelogin.utils;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
public class AppConstants {

    public static final int MIN_PASSWORD_LENGTH = 6;
    public static String APP_PREF_NAME = "sample-login-pref";

    public enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_NO_SESSION(1),
        LOGGED_IN_MODE_SERVER(2);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
