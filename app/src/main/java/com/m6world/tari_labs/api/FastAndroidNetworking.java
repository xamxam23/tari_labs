package com.m6world.tari_labs.api;

import android.util.Log;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.jacksonandroidnetworking.JacksonParserFactory;
import okhttp3.*;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.m6world.tari_labs.api.TimeOutParameters.*;

@Deprecated
public class FastAndroidNetworking {
    private static JacksonParserFactory jaksonParser;
    private static OkHttpClient.Builder httpBuilder = new OkHttpClient().newBuilder()
            .addInterceptor(new LoggingInterceptor())
            .connectTimeout(Companion.getCONNECT_DEFAULT(), TimeUnit.SECONDS)
            .writeTimeout(Companion.getWRITE_DEFAULT(), TimeUnit.SECONDS)
            .readTimeout(Companion.getREAD_DEFAULT(), TimeUnit.SECONDS);

    public static boolean shouldRecordResponse = true;

    public static OkHttpClient.Builder getRecorderOkHttpClient(String requestClassName, String responseClassName) {
        return new OkHttpClient().newBuilder()
                .addInterceptor(new LoggingInterceptor(requestClassName, responseClassName))
                .connectTimeout(Companion.getCONNECT_DEFAULT(), TimeUnit.SECONDS)
                .writeTimeout(Companion.getWRITE_DEFAULT(), TimeUnit.SECONDS)
                .readTimeout(Companion.getREAD_DEFAULT(), TimeUnit.SECONDS);
    }

    public static <T> ANResponse<T> post(String url, Object requestObject, Class<T> responseClass) {
        return post(url, requestObject, responseClass, null);
    }

    public synchronized static <T> ANResponse<T> post(String url, Object requestObject, Class<T> responseClass,
                                                      TimeOutParameters timeOutParameters) {
        setJsonParser();
        setupShouldRecord(requestObject, responseClass);
        ANRequest request = AndroidNetworking.post(url)
                .addApplicationJsonBody(requestObject)
                .addBodyParameter(requestObject) // posting java object
                .setTag(requestObject.getClass().getName())
                .setOkHttpClient(httpBuilder.build())
                .build();

        setTimeOutParameters(httpBuilder, timeOutParameters);
        ANResponse<T> anResponse = request.executeForObject(responseClass);

        Log.d("x-i", String.format(Locale.US, "[%s, %s]", requestObject.getClass().getSimpleName(), responseClass.getSimpleName()));
        return anResponse;
    }

    private static <T> void setupShouldRecord(Object requestObject, Class<T> responseClass) {
        setupShouldRecord(requestObject.getClass().getSimpleName(), responseClass.getSimpleName());
    }

    private static void setupShouldRecord(String requestName, String responseName) {
        if (shouldRecordResponse)
            httpBuilder = getRecorderOkHttpClient(requestName, responseName);
    }

    public synchronized static <T> ANResponse<T> get(String url, String requestName, final Map<String, String> headerMap,
                                                     Class<T> responseClass, TimeOutParameters timeOutParameters) {
        setJsonParser();
        setupShouldRecord(requestName, responseClass);
        ANRequest request = AndroidNetworking.get(url)
                .addHeaders(headerMap)
                .setTag(requestName)
                .build();

        setTimeOutParameters(httpBuilder, timeOutParameters);
        ANResponse<T> anResponse = request.executeForObject(responseClass);

        Log.d("x-i", String.format(Locale.US, "[%s, %s]", requestName, responseClass.getSimpleName()));
        return anResponse;
    }

    private static void setAuthenticator(OkHttpClient.Builder builder, final Map<String, String> headerMap) {
        builder.authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                return response.request().newBuilder()
                        .header("Authorization", headerMap.get("Authorization"))
                        .build();
            }
        });
    }

    private static void setJsonParser() {
        if (jaksonParser == null) {
            ObjectMapper mapper = new ObjectMapper()
                    .setPropertyNamingStrategy(PropertyNamingStrategy.SnakeCaseStrategy.SNAKE_CASE)
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            jaksonParser = new JacksonParserFactory(mapper);
        }
        AndroidNetworking.setParserFactory(jaksonParser);
    }

    private static void setTimeOutParameters(OkHttpClient.Builder builder, TimeOutParameters timeOutParameters) {
        if (timeOutParameters != null)
            builder.connectTimeout(timeOutParameters.getConnect(), TimeUnit.SECONDS)
                    .readTimeout(timeOutParameters.getRead(), TimeUnit.SECONDS)
                    .writeTimeout(timeOutParameters.getWrite(), TimeUnit.SECONDS);
    }
}