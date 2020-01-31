package com.cemerci.samplelogin.di.builder;

import com.cemerci.samplelogin.ui.login.LoginActivity;
import com.cemerci.samplelogin.ui.main.MainActivity;
import com.cemerci.samplelogin.ui.register.RegisterActivity;
import com.cemerci.samplelogin.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector()
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector()
    abstract RegisterActivity bindRegisterActivity();

    @ContributesAndroidInjector()
    abstract MainActivity bindMainActivity();

}
