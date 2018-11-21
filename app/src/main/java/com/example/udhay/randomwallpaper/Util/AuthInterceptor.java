package com.example.udhay.randomwallpaper.Util;

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

        Request newRequest = request.newBuilder().addHeader("Authorization","Client-ID "+clientId ).build();

        HttpUrl url = request.url().newBuilder().addQueryParameter("client_id" ,clientId ).build();

        newRequest = newRequest.newBuilder().url(url).build();


        return chain.proceed(newRequest);
    }
}
