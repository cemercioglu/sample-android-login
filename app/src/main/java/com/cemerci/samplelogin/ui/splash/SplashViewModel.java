package com.cemerci.samplelogin.ui.splash;

import com.cemerci.samplelogin.data.DataManager;
import com.cemerci.samplelogin.ui.base.BaseViewModel;
import com.cemerci.samplelogin.utils.rx.SchedulerProvider;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
public class SplashViewModel extends BaseViewModel<SplashListener> {

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void checkLoginStatus() {
        switch (getDataManager().getUserMode()) {

            case LOGGED_IN_MODE_SERVER:
                getListener().openMainActivity();
                break;
            case LOGGED_IN_MODE_LOGGED_OUT:
            case LOGGED_IN_MODE_NO_SESSION:
                getListener().openLoginActivity();
                break;
        }
    }
}
