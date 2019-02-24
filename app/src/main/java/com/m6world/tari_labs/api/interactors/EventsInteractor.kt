package com.m6world.tari_labs.api.interactors

import com.m6world.tari_labs.api.contracts.IEventsInteractor
import com.m6world.tari_labs.api.models.EventCheckinsResponse
import io.reactivex.Observable

import java.util.HashMap

class EventsInteractor : IEventsInteractor {
    internal var getNetworkInteractor = GetNetworkInteractor()

    private val END_POINT = "events/checkins"

    override fun getEventCheckins(token: String?): Observable<EventCheckinsResponse> {
        val headerMap = HashMap<String, String>()
        headerMap["Authorization"] = "Bearer $token"
        return getNetworkInteractor.createObservable(
            END_POINT,
            "GetEventCheckins",
            headerMap,
            EventCheckinsResponse::class.java
        )
    }
}