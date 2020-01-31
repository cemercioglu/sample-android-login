package com.cemerci.samplelogin.ui.main;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-31.
 */
public interface MainListener {
    void loggedOut();

    void updateUser(String userId, boolean isRemoved);

    void takePhoto();

}
