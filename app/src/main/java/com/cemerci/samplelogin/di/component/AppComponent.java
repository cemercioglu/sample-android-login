package com.cemerci.samplelogin.di.component;

import android.app.Application;

import com.cemerci.samplelogin.App;
import com.cemerci.samplelogin.di.builder.ActivityBuilder;
import com.cemerci.samplelogin.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by cem.ercioglu@asiselektronik.com.tr on 2020-01-21.
 */

@Singleton
@Component(modules = {AppModule.class, ActivityBuilder.class, AndroidInjectionModule.class})
public interface AppComponent {

    void inject(App app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
