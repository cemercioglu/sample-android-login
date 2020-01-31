package com.cemerci.samplelogin.di.module;

import android.app.Application;
import android.content.Context;

import com.cemerci.samplelogin.data.AppDataManager;
import com.cemerci.samplelogin.data.DataManager;
import com.cemerci.samplelogin.data.firebase.AppFirebaseHelper;
import com.cemerci.samplelogin.data.firebase.FirebaseHelper;
import com.cemerci.samplelogin.data.prefs.AppPreferencesHelper;
import com.cemerci.samplelogin.data.prefs.PreferencesHelper;
import com.cemerci.samplelogin.utils.rx.AppSchedulerProvider;
import com.cemerci.samplelogin.utils.rx.SchedulerProvider;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-28.
 */
@Module
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    FirebaseHelper provideFirebaseHelper(AppFirebaseHelper appFirebaseHelper) {
        return appFirebaseHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }
}
