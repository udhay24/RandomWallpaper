package com.example.udhay.randomwallpaper.api;

import com.example.udhay.randomwallpaper.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotoApi {

    @GET("photos/random")
    Call<Photo> getRandomPhoto();

    @GET("photos")
    Call<List<Photo>> getPhotos(@Query("page") int page, @Query("per_page") int perPage);



}
