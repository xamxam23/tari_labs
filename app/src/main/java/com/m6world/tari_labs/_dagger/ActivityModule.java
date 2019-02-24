package com.m6world.tari_labs._dagger;

import com.m6world.tari_labs.MainActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    @Provides
    MainActivity provideMainActivity(MainActivity activity) {
        return activity;
    }
}