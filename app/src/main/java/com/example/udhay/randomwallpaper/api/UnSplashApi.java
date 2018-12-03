package com.example.udhay.randomwallpaper.api;

import com.example.udhay.randomwallpaper.model.Collection;
import com.example.udhay.randomwallpaper.model.CollectionSearchResult;
import com.example.udhay.randomwallpaper.model.Photo;
import com.example.udhay.randomwallpaper.model.PhotoSearchResult;
import com.example.udhay.randomwallpaper.model.User;
import com.example.udhay.randomwallpaper.model.UserSearchResult;
import com.example.udhay.randomwallpaper.model.UserStatistics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UnSplashApi {

    @GET("photos/random")
    Call<Photo> getRandomPhoto();

    @GET("photos")
    Call<List<Photo>> getPhotos(@Query("page") int page, @Query("per_page") int perPage);

    @GET("collections")
    Call<List<Collection>> getCollections(@Query("page") int page, @Query("per_page") int perPage);

    @GET("search/photos")
    Call<PhotoSearchResult> searchPhotos(@Query("query") String Query, @Query("page") int page, @Query("per_page") int perPage
            , @Query("collections") String collection, @Query("orientation") String orientation);

    @GET("search/collections")
    Call<CollectionSearchResult> searchCollection(@Query("query") String query, @Query("page") int page, @Query("per_page") int perPage);

    @GET("users/{user_name}")
    Call<User> getUser(@Path("user_name") String userName);

    @GET("search/users")
    Call<UserSearchResult> searchUser(@Query("query") String query, @Query("page") int page, @Query("per_page") int perPage);

    @GET("photos/{id}")
    Call<Photo> getPhotoById(@Path("id") String id);

    @GET("collections/{id}/photos")
    Call<List<Photo>> getCollectionById(@Path("id") int id, @Query("page") int page, @Query("per_page") int perPage);

    @GET("users/{user_name}/statistics")
    Call<UserStatistics> getUserStatistics(@Path("user_name") String userName);

}

class UnsplashModel {

    public enum PhotoOrientation {
        landscape, portrait, squarish
    }
}
