package com.m6world.tari_labs.api._interactors;

import com.m6world.tari_labs.api._contracts.IEventsInteractor;
import com.m6world.tari_labs.api.models.EventCheckinsResponse;
import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class EventsInteractor implements IEventsInteractor {
    GetNetworkInteractor getNetworkInteractor = new GetNetworkInteractor();

    private final String END_POINT = "events/checkins";

    @NotNull
    @Override
    public Observable<EventCheckinsResponse> getEventCheckins(@NotNull String token) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Bearer " + token);
        return getNetworkInteractor.createObservable(END_POINT, "GetEventCheckins", headerMap, EventCheckinsResponse.class);
    }
}