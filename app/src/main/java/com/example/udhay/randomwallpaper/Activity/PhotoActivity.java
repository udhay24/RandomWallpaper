package com.example.udhay.randomwallpaper.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.Util.RetrofitClient;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoActivity extends AppCompatActivity {

    public static final String ID = "photo_id";
    private static final int SET_WALLPAPER_ID = 24;
    @BindView(R.id.image_view)
    ImageView imageView;
    private String id;
    private Photo selectedPhoto;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private List<Photo> relatedPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        id = getIntent().getStringExtra(ID);

        loadImage();

    }

    private void loadImage() {

        UnSplashApi unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

        unSplashApi.getPhotoById(id).enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {

                selectedPhoto = response.body();
                Picasso.get()
                        .load(selectedPhoto.getUrls().getRegular())
                        .into(imageView);
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, SET_WALLPAPER_ID, Menu.NONE, "Set Wallpaper");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == SET_WALLPAPER_ID) {
            setWallpaper();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setWallpaper() {

    }

}
