package com.example.udhay.randomwallpaper.Util;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    static Retrofit retrofit = null;
    private static final String CLIENT_ID = "374a680ea9dfd39c388e1411ba9229adfea3b257026d7b44bc7d053ca6729bb9";

    private RetrofitClient(){}

    public static Retrofit getClient() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(CLIENT_ID))
                .build();

        final String BASE_URL = "http://api.unsplash.com/";


        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;

    }
}
