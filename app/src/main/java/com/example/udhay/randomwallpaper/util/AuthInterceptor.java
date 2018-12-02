package com.example.udhay.randomwallpaper.util;

import java.io.IOException;

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

        HttpUrl url = request.url().newBuilder().addQueryParameter("client_id", clientId).build();

        request = request.newBuilder()
                .header("Authorization", "Client-ID " + clientId)
                .url(url)
                .build();

        return chain.proceed(request);

    }
}
