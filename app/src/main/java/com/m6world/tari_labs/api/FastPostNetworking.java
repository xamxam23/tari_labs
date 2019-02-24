package com.m6world.tari_labs.api;

import android.util.Log;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;

import java.util.Locale;
import java.util.Map;

public class FastPostNetworking extends AFastNetworking {
    public synchronized <T> ANResponse<T> posst(String url, Object requestObject, Class<T> responseClass,
                                                TimeOutParameters timeOutParameters) {
        OkHttpClient.Builder httpBuilder = createDefaultBuilder();
        String requestName = requestObject.getClass().getSimpleName();
        String responseName = responseClass.getSimpleName();
        attachRecorder(httpBuilder, requestName, responseName);
        ANRequest request = AndroidNetworking.post(url)
                .addApplicationJsonBody(requestObject)
                .addBodyParameter(requestObject) // posting java object
                .setTag(requestObject.getClass().getName())
                .setOkHttpClient(httpBuilder.build())
                .build();
        setTimeOutParameters(httpBuilder, timeOutParameters);

        ANResponse<T> anResponse = request.executeForObject(responseClass);

        Log.d("x-i", String.format(Locale.US, "[%s, %s]", requestName, responseClass.getSimpleName()));
        return anResponse;
    }
}