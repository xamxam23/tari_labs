package com.m6world.tari_labs._dagger;

import com.m6world.tari_labs.features.AccountsFragment;
import com.m6world.tari_labs.features.TicketsFragment;
import com.m6world.tari_labs.features._events.EventsFragment;
import com.m6world.tari_labs.features._home.HomeFragment;
import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    @Provides
    HomeFragment provideHomeView(HomeFragment fragment) {
        return fragment;
    }

    @Provides
    EventsFragment provideEventsView(EventsFragment fragment) {
        return fragment;
    }

    @Provides
    TicketsFragment provideTicketsView(TicketsFragment fragment) {
        return fragment;
    }

    @Provides
    AccountsFragment provideAccountsView(AccountsFragment fragment) {
        return fragment;
    }
}