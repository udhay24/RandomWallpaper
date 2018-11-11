package com.example.udhay.randomwallpaper.Util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {

    String ClientId;
    public BasicAuthInterceptor(String authId){
        ClientId = authId;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request newRequest = request.newBuilder().addHeader("Authorization" , ClientId).build();

        return chain.proceed(newRequest);
    }
}
