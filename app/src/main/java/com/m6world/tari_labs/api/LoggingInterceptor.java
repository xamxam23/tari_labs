package com.m6world.tari_labs.api;

import android.util.Log;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;

import java.io.IOException;
import java.nio.charset.Charset;

public class LoggingInterceptor implements Interceptor {
    private String requestClassName = "", responseClassName = "";

    public LoggingInterceptor(String requestClassName, String responseClassName) {
        this.requestClassName = requestClassName;
        this.responseClassName = responseClassName;
    }

    public LoggingInterceptor() {
    }

    public void setRequestClassName(String requestClassName) {
        this.requestClassName = requestClassName;
    }

    public void setResponseClassName(String responseClassName) {
        this.responseClassName = responseClassName;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        Log.i("x-i", String.format("Request %s", request.url()));
        String req = bodyToString(request.body());
        if (req == null || req.isEmpty())
            if (request.headers() != null)
                req = request.headers().toString();
        log(requestClassName, req);
        Log.d("x-i", "Request(" + requestClassName + ") = " + req);
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.i("x-i", String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, to(response.body())));
        return response;
    }

    public static void log(String name, String payload) {
        Log.i("x-i", String.format("%s: %s", name, payload));
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            copy.writeTo(buffer);
            return buffer.readUtf8();
        } catch (final NullPointerException | IOException e) {
            return "";
        }
    }

    public String to(ResponseBody responseBody) throws IOException {
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        String payload = buffer.clone().readString(Charset.defaultCharset());
        log(responseClassName, payload);
        return payload;
    }
}