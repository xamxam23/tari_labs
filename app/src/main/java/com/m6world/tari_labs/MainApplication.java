package com.m6world.tari_labs;

import com.androidnetworking.AndroidNetworking;
import com.m6world.tari_labs._dagger.AppComponent;
import com.m6world.tari_labs._dagger.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MainApplication extends DaggerApplication {
    private static MainApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        AndroidNetworking.initialize(getApplicationContext());
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    public static MainApplication getInstance() {
        return app;
    }
}