package com.example.udhay.randomwallpaper.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.udhay.randomwallpaper.Adapters.FeaturedImageAdapter;
import com.example.udhay.randomwallpaper.Listeners.EndlessScrollListener;
import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.Util.RetrofitClient;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.model.Photo;

import java.util.List;

import jp.wasabeef.recyclerview.animators.ScaleInTopAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedImages extends Fragment {

    public static final String FRAGMENT_TITLE = "Featured";

    ProgressBar progressBar;

    RecyclerView recyclerView;

    private FeaturedImageAdapter featuredImageAdapter;
    private GridLayoutManager gridLayoutManager;

    public FeaturedImages() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layoutView = inflater.inflate(R.layout.fragment_featured_images, container, false);

        progressBar = layoutView.findViewById(R.id.progress_bar);
        recyclerView = layoutView.findViewById(R.id.featured_images_recycler_view);

        return layoutView;
    }

    @Override
    public void onStart() {
        super.onStart();

        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        gridLayoutManager = new GridLayoutManager(this.getContext(), 2);

        recyclerView.setLayoutManager(gridLayoutManager);

        loadImages();
        recyclerView.addOnScrollListener(getScrollListener());
        recyclerView.setItemAnimator(new ScaleInTopAnimator());

    }


    private void loadImages() {

        UnSplashApi unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

        unSplashApi.getPhotos(1, 10).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                progressBar.setVisibility(View.GONE);
                featuredImageAdapter = new FeaturedImageAdapter(response.body());
                recyclerView.setAdapter(featuredImageAdapter);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Toast.makeText(FeaturedImages.this.getContext(), "Not Able to load Images", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private EndlessScrollListener getScrollListener() {

        EndlessScrollListener endlessScrollListener = new EndlessScrollListener(gridLayoutManager) {

            boolean load = true;

            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                UnSplashApi unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

                unSplashApi.getPhotos(page, 20).enqueue(new Callback<List<Photo>>() {
                    @Override
                    public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                        if (response.body() != null) {
                            List<Photo> photos = response.body();
                            featuredImageAdapter.addPhotoList(photos);
                            load = true;
                        } else {
                            load = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Photo>> call, Throwable t) {
                    }
                });

                return load;
            }
        };
        return endlessScrollListener;
    }
}
