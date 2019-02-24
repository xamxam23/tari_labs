package com.m6world.tari_labs.api;

import android.util.Log;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import okhttp3.OkHttpClient;

import java.util.Locale;
import java.util.Map;

public class FastGetNetworking extends AFastNetworking {
    public synchronized <T> ANResponse<T> get(String url, String requestName, final Map<String, String> headerMap,
                                              Class<T> responseClass, TimeOutParameters timeOutParameters) {
        OkHttpClient.Builder httpBuilder = createDefaultBuilder();
        attachRecorder(httpBuilder, requestName, responseClass.getName());
        ANRequest request = AndroidNetworking.get(url)
                .setOkHttpClient(httpBuilder.build())
                .addHeaders(headerMap)
                .setTag(requestName)
                .build();

        setTimeOutParameters(httpBuilder, timeOutParameters);

        ANResponse<T> anResponse = request.executeForObject(responseClass);

        Log.d("x-i", String.format(Locale.US, "[%s, %s]", requestName, responseClass.getSimpleName()));
        return anResponse;
    }
}