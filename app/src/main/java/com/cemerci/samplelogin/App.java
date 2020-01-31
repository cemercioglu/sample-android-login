package com.cemerci.samplelogin;

import android.app.Application;

import com.cemerci.samplelogin.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-21.
 */
public class App extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> objectDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return objectDispatchingAndroidInjector;
    }
}
