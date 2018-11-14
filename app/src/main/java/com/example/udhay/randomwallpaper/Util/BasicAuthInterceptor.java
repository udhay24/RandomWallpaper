package com.example.udhay.randomwallpaper.Util;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {

    String clientId;
    public BasicAuthInterceptor(String authId){
        clientId = authId;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request newRequest = request.newBuilder().addHeader("Authorization","Client-ID 374a680ea9dfd39c388e1411ba9229adfea3b257026d7b44bc7d053ca6729bb9" ).build();

        HttpUrl url = request.url().newBuilder().addQueryParameter("client_id" ,clientId ).build();

        newRequest = newRequest.newBuilder().url(url).build();


        return chain.proceed(newRequest);
    }
}
