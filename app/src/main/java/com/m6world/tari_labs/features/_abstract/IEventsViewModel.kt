package com.m6world.tari_labs.features._abstract

import com.m6world.tari_labs.api.contracts.IEventsInteractor
import com.m6world.tari_labs.api.interactors.EventsInteractor
import com.m6world.tari_labs.api.models.EventCheckinsResponse
import io.reactivex.Observable

interface IEventsViewModel {
    fun getEventCheckins(token: String?): Observable<EventCheckinsResponse>
}