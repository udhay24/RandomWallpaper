package com.example.udhay.randomwallpaper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.udhay.randomwallpaper.Adapters.ImageAdapter;
import com.example.udhay.randomwallpaper.Listeners.EndlessScrollListener;
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

    ImageAdapter imageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);


        gridLayoutManager.setSmoothScrollbarEnabled(true);

        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addOnScrollListener(new EndlessScrollListener(gridLayoutManager){
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {

                final boolean loading;

                PhotoApi photoApi = com.example.udhay.randomwallpaper.Util.RetrofitClient.getClient().create(PhotoApi.class);
                photoApi.getPhotos(page , 10).enqueue(new Callback<List<Photo>>() {
                    @Override
                    public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                        if(response.body() != null){
                            List<Photo> photos = response.body();
                            if(imageAdapter != null){
                                imageAdapter.addPhotoList(photos);

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Photo>> call, Throwable t) {

                    }
                });
                return true;
            }
        });
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

                imageAdapter = new ImageAdapter(response.body());
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(imageAdapter);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });
    }
}
