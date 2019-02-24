package com.m6world.tari_labs.features._events

import com.m6world.tari_labs.api.contracts.IEventsInteractor
import com.m6world.tari_labs.api.models.EventCheckinsResponse
import com.m6world.tari_labs.features._abstract.BaseViewModel
import com.m6world.tari_labs.features._abstract.IEventsViewModel
import io.reactivex.Observable
import javax.inject.Inject

class EventsViewModel : BaseViewModel(), IEventsViewModel {
    @set:Inject var eventsInteractor: IEventsInteractor? = null

    init {
        component.inject(this)
    }

    override fun getEventCheckins(token: String?): Observable<EventCheckinsResponse> {
        return eventsInteractor!!.getEventCheckins(token)
    }
}