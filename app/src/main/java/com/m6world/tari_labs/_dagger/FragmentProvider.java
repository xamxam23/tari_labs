package com.m6world.tari_labs._dagger;

import com.m6world.tari_labs.features.AccountsFragment;
import com.m6world.tari_labs.features.TicketsFragment;
import com.m6world.tari_labs.features._events.EventsFragment;
import com.m6world.tari_labs.features._home.HomeFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentProvider {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract HomeFragment provideHomeFragment();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract EventsFragment provideEventFragment();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract TicketsFragment provideTicketsFragment();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract AccountsFragment provideAccountsFragment();

}
