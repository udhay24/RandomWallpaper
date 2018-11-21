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

import com.example.udhay.randomwallpaper.Adapters.ImageAdapter;
import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.api.PhotoApi;
import com.example.udhay.randomwallpaper.model.Photo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedImages extends Fragment {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.featured_images_recycler_view)
    RecyclerView recyclerView;

    private ImageAdapter imageAdapter;

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
        ButterKnife.bind(layoutView);

        return layoutView;
    }

    @Override
    public void onStart() {
        super.onStart();

        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);

        recyclerView.setLayoutManager(gridLayoutManager);

        loadImages();
    }

    private void loadImages() {

        PhotoApi photoApi = com.example.udhay.randomwallpaper.Util.RetrofitClient.getClient().create(PhotoApi.class);

        photoApi.getPhotos(1, 10).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                progressBar.setVisibility(View.GONE);
                imageAdapter = new ImageAdapter(response.body());
                recyclerView.setAdapter(imageAdapter);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Toast.makeText(FeaturedImages.this.getContext(), "Not Able to load Images", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
