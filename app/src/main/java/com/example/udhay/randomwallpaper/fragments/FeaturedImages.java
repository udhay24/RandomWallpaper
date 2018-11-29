package com.example.udhay.randomwallpaper.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.activity.PhotoDetailActivity;
import com.example.udhay.randomwallpaper.adapters.FeaturedImageAdapter;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.interfaces.ClickInterface;
import com.example.udhay.randomwallpaper.listeners.EndlessScrollListener;
import com.example.udhay.randomwallpaper.model.Photo;
import com.example.udhay.randomwallpaper.util.GifImageView;
import com.example.udhay.randomwallpaper.util.RetrofitClient;

import java.util.List;

import jp.wasabeef.recyclerview.animators.ScaleInTopAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedImages extends Fragment {

    public static final String FRAGMENT_TITLE = "Featured";

    RecyclerView recyclerView;

    GifImageView progressGifView;
    ImageView errorImage;

    private FeaturedImageAdapter featuredImageAdapter;
    private GridLayoutManager gridLayoutManager;

    UnSplashApi unSplashApi;

    public FeaturedImages() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View layoutView = inflater.inflate(R.layout.fragment_featured_images, container, false);
        recyclerView = layoutView.findViewById(R.id.featured_images_recycler_view);

        gridLayoutManager = new GridLayoutManager(this.getActivity(), 2);

        recyclerView.setLayoutManager(gridLayoutManager);

        progressGifView = layoutView.findViewById(R.id.preloader_1_GifView);
        errorImage = layoutView.findViewById(R.id.error_image);

        displayLoading();

        loadInitialImages();

        recyclerView.addOnScrollListener(getScrollListener());
        recyclerView.setItemAnimator(new ScaleInTopAnimator());

        return layoutView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private void loadInitialImages() {

        unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

        unSplashApi.getPhotos(1, 10).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                featuredImageAdapter = new FeaturedImageAdapter(response.body(), new ClickInterface() {
                    @Override
                    public void onClick(View view) {

                        int position = recyclerView.getChildAdapterPosition(view);

                        Intent intent = new Intent(FeaturedImages.this.getContext(), PhotoDetailActivity.class);

                        intent.putExtra(PhotoDetailActivity.ID, featuredImageAdapter.getList().get(position).getId());
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(featuredImageAdapter);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

                displayError();
                Toast.makeText(FeaturedImages.this.getContext(), "Unable to load Images", Toast.LENGTH_SHORT).show();
            }
        });

        displayImages();

    }


    private EndlessScrollListener getScrollListener() {

        EndlessScrollListener endlessScrollListener = new EndlessScrollListener(gridLayoutManager) {

            private boolean load = true;

            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

                unSplashApi.getPhotos(page, 20).enqueue(new Callback<List<Photo>>() {
                    @Override
                    public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                        if (response.body() != null && response.errorBody() == null) {

                            List<Photo> photos = response.body();
                            featuredImageAdapter.addPhotoList(photos);
                            load = true;

                        } else {

                            displayError();
                            Toast.makeText(FeaturedImages.this.getContext(), "Error loading images", Toast.LENGTH_SHORT).show();
                            load = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Photo>> call, Throwable t) {

                        displayError();
                        Toast.makeText(FeaturedImages.this.getContext(), "Unable to load images", Toast.LENGTH_SHORT).show();
                        load = false;

                    }
                });

                return load;
            }
        };
        return endlessScrollListener;
    }

    private void displayError() {

        recyclerView.setVisibility(View.GONE);
        progressGifView.setVisibility(View.GONE);
        errorImage.setVisibility(View.VISIBLE);
    }

    private void displayImages() {

        recyclerView.setVisibility(View.VISIBLE);
        progressGifView.setVisibility(View.GONE);
        errorImage.setVisibility(View.GONE);
    }

    private void displayLoading() {

        progressGifView.setGifImageResource(R.drawable.preloader_1);

        recyclerView.setVisibility(View.GONE);
        progressGifView.setVisibility(View.VISIBLE);
        errorImage.setVisibility(View.GONE);
    }

}
