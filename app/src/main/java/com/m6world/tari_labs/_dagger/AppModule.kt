package com.m6world.tari_labs._dagger

import com.m6world.tari_labs.api.contracts.IAuthInteractor
import com.m6world.tari_labs.api.contracts.IEventsInteractor
import com.m6world.tari_labs.api.interactors.AuthInteractor
import com.m6world.tari_labs.api.interactors.EventsInteractor
import com.m6world.tari_labs.features._abstract.IEventsViewModel
import com.m6world.tari_labs.features._home.HomeViewModel
import com.m6world.tari_labs.features._abstract.IHomeViewModel
import com.m6world.tari_labs.features._events.EventsViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    val authInteractor: IAuthInteractor
        @Provides get() = AuthInteractor()

    val eventsInteractor: IEventsInteractor
        @Provides get() = EventsInteractor()

    val homeViewModel: IHomeViewModel
        @Provides get() = HomeViewModel()

    val eventsViewModel: IEventsViewModel
        @Provides get() = EventsViewModel()
}