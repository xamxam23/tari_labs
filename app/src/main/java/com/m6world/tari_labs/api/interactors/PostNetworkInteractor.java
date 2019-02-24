package com.m6world.tari_labs.api.interactors;

import com.androidnetworking.common.ANResponse;
import com.m6world.tari_labs.api.FastPostNetworking;
import io.reactivex.Observable;

import java.util.concurrent.Callable;

public class PostNetworkInteractor extends ANetworkInteractor {
    FastPostNetworking postNetworking = new FastPostNetworking();

    public <T> Observable<T> createObservable(final String endPoint, final Object request, final Class<T> responseClass) {
        return Observable.fromCallable(new Callable<T>() {
            public T call() throws Exception {
                ANResponse<T> responseEntity = postNetworking.posst(getBaseUrl() + endPoint, request, responseClass, getTimeOutParameters());
                if (responseEntity.isSuccess()) {
                    return responseEntity.getResult();
                } else
                    return onFailure(responseEntity);
            }
        });
    }
}