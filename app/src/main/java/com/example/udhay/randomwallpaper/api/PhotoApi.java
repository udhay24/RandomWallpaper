package com.example.udhay.randomwallpaper.api;

import com.example.udhay.randomwallpaper.model.Photo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoApi {

    @GET("photos/random")
    Call<Photo> getRandomPhoto();

}
