package com.m6world.tari_labs.api.interactors;

import com.androidnetworking.common.ANResponse;
import com.m6world.tari_labs.api.FastAndroidNetworking;
import com.m6world.tari_labs.api.FastGetNetworking;
import io.reactivex.Observable;

import java.util.Map;
import java.util.concurrent.Callable;

public class GetNetworkInteractor extends ANetworkInteractor {
    FastGetNetworking getNetworking = new FastGetNetworking();

    public <T> Observable<T> createObservable(final String endPoint, final String requestName, final Map<String, String> headesMap, final Class<T> responseClass) {
        return Observable.fromCallable(new Callable<T>() {
            public T call() throws Exception {
                ANResponse<T> responseEntity = get(endPoint, requestName, headesMap, responseClass);
                if (responseEntity.isSuccess()) {
                    return responseEntity.getResult();
                }
                return onFailure(responseEntity);
            }
        });
    }

    @Deprecated
    private <T> ANResponse<T> get_(final String endPoint, final String requestName, final Map<String, String> headesMap, final Class<T> responseClass) {
        return FastAndroidNetworking.get(getBaseUrl() + endPoint, requestName, headesMap, responseClass, getTimeOutParameters());
    }

    private <T> ANResponse<T> get(final String endPoint, final String requestName, final Map<String, String> headesMap, final Class<T> responseClass) {
        return getNetworking.get(getBaseUrl() + endPoint, requestName, headesMap, responseClass, getTimeOutParameters());
    }
}