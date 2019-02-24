package com.m6world.tari_labs.api._contracts

import com.m6world.tari_labs.api.models.EventCheckinsResponse
import io.reactivex.Observable

interface IEventsInteractor {
    fun getEventCheckins(token: String?): Observable<EventCheckinsResponse>
}