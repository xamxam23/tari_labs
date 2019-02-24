package com.m6world.tari_labs.features._events

import com.m6world.tari_labs.api.contracts.IEventsInteractor
import com.m6world.tari_labs.api.interactors.EventsInteractor
import com.m6world.tari_labs.api.models.EventCheckinsResponse
import io.reactivex.Observable

class EventsViewModel {
    var eventsInteractor: IEventsInteractor = EventsInteractor()

    fun getEventCheckins(token: String?): Observable<EventCheckinsResponse> {
        return eventsInteractor.getEventCheckins(token)
    }
}