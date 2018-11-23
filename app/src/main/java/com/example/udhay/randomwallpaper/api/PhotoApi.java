package com.example.udhay.randomwallpaper.api;

import com.example.udhay.randomwallpaper.model.Collection;
import com.example.udhay.randomwallpaper.model.Photo;
import com.example.udhay.randomwallpaper.model.PhotoSearchResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotoApi {

    @GET("photos/random")
    Call<Photo> getRandomPhoto();

    @GET("photos")
    Call<List<Photo>> getPhotos(@Query("page") int page, @Query("per_page") int perPage);

    @GET("collections")
    Call<List<Collection>> getCollections(@Query("page") int page, @Query("per_page") int perPage);

    @GET("search/photos")
    Call<PhotoSearchResult> searchPhotos(@Query("query") String Query, @Query("page") int page, @Query("per_page") int perPage
            , @Query("collections") String collection, @Query("orientation") String orientation);

}

class UnsplashModel {

    public enum collections {
        landscape, portrait, squarish
    }
}
