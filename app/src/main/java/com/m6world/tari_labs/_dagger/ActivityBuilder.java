package com.m6world.tari_labs._dagger;

import com.m6world.tari_labs.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = {ActivityModule.class, FragmentProvider.class})
    abstract MainActivity bindMainActivity();
}