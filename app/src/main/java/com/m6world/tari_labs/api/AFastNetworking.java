package com.m6world.tari_labs.api;

import android.util.Log;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.jacksonandroidnetworking.JacksonParserFactory;
import okhttp3.OkHttpClient;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.m6world.tari_labs.api.TimeOutParameters.Companion;

public abstract class AFastNetworking {
    static {
        final ObjectMapper mapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        final JacksonParserFactory jaksonParser = new JacksonParserFactory(mapper);
        AndroidNetworking.setParserFactory(jaksonParser);
    }

    OkHttpClient.Builder createDefaultBuilder() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(Companion.getCONNECT_DEFAULT(), TimeUnit.SECONDS)
                .writeTimeout(Companion.getWRITE_DEFAULT(), TimeUnit.SECONDS)
                .readTimeout(Companion.getREAD_DEFAULT(), TimeUnit.SECONDS);
    }

    void attachRecorder(OkHttpClient.Builder httpBuilder, String requestClassName, String responseClassName) {
        httpBuilder.addInterceptor(new LoggingInterceptor(requestClassName, responseClassName));
    }

    protected void setTimeOutParameters(OkHttpClient.Builder builder, TimeOutParameters timeOutParameters) {
        if (timeOutParameters != null)
            builder.connectTimeout(timeOutParameters.getConnect(), TimeUnit.SECONDS)
                    .readTimeout(timeOutParameters.getRead(), TimeUnit.SECONDS)
                    .writeTimeout(timeOutParameters.getWrite(), TimeUnit.SECONDS);
    }
}