package com.cemerci.samplelogin.ui.register;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
public interface RegisterListener {
    void takePhoto();

    void register();

    void registerSuccess();

    void registerUserExists();
}
