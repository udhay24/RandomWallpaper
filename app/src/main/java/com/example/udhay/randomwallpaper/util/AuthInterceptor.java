package com.example.udhay.randomwallpaper.util;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private String clientId;

    public AuthInterceptor(String authId){
        clientId = authId;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Headers headers = request.headers().newBuilder().add("Authorization", "Client-ID " + clientId).build();

        request = request.newBuilder().headers(headers).build();

        HttpUrl url = request.url().newBuilder().addQueryParameter("client_id", clientId).build();

        request = request.newBuilder().url(url).build();

        return chain.proceed(request);

    }
}
