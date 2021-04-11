package com.creativedeveloper.ecommerce.data.remote.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalUrl = originalRequest.url();
        HttpUrl url = originalUrl.newBuilder()
                .build();

        Request.Builder requestBuilder = originalRequest.newBuilder().url(url);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}

