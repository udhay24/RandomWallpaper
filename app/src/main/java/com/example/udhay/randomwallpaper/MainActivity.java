package com.example.udhay.randomwallpaper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.udhay.randomwallpaper.Adapters.ImageAdapter;
import com.example.udhay.randomwallpaper.Util.RetrofitClient;
import com.example.udhay.randomwallpaper.api.PhotoApi;
import com.example.udhay.randomwallpaper.model.Photo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.featured_images)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    List<Photo> photoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);


        gridLayoutManager.setSmoothScrollbarEnabled(true);

        recyclerView.setLayoutManager(gridLayoutManager);

        getData();
    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    private void getData() {
        PhotoApi photoApi = RetrofitClient.getClient().create(PhotoApi.class);
        Call<List<Photo>> photos = photoApi.getPhotos(1, 100);

        photos.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                ImageAdapter imageAdapter = new ImageAdapter(MainActivity.this, response.body());
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(imageAdapter);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });
    }
}
