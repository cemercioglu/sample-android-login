package com.cemerci.samplelogin.ui.login;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
public interface LoginListener {
    void handleError(Throwable throwable);

    void openRegisterActivity();

    void login();

    void userNotFound();

    void openMainActivity();

}
