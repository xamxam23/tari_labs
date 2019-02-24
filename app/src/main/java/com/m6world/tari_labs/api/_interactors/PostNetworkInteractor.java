package com.m6world.tari_labs.api._interactors;

import com.androidnetworking.common.ANResponse;
import com.m6world.tari_labs.api.FastAndroidNetworking;
import io.reactivex.Observable;

import java.util.concurrent.Callable;

public class PostNetworkInteractor extends ANetworkInteractor {
    public <T> Observable<T> createObservable(final String endPoint, final Object request, final Class<T> responseClass) {
        return Observable.fromCallable(new Callable<T>() {
            public T call() throws Exception {
                ANResponse<T> responseEntity = FastAndroidNetworking.post(getBaseUrl() + endPoint, request, responseClass, getTimeOutParameters());
                if (responseEntity.isSuccess()) {
                    return responseEntity.getResult();
                }
                return onFailure(responseEntity);
            }
        });
    }
}