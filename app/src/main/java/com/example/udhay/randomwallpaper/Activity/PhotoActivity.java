package com.example.udhay.randomwallpaper.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.Util.RetrofitClient;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.model.Photo;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoActivity extends AppCompatActivity {

    public static final String ID = "photo_id";
    private static final int SET_WALLPAPER_ID = 24;

    private String id;
    private Photo selectedPhoto;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.save)
    TextView saveTextView;

    @BindView(R.id.download)
    TextView downloadTextView;

    @BindView(R.id.share)
    TextView shareTextView;

    @BindView(R.id.description)
    TextView description;
    

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

        saveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage();
            }
        });

        downloadTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage();
            }
        });

        shareTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareImage();
            }
        });

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

        Toast.makeText(this, "Set Wallpaper", Toast.LENGTH_SHORT).show();
    }

    private void saveImage() {

        Toast.makeText(PhotoActivity.this, "Saved", Toast.LENGTH_SHORT).show();
    }

    private void shareImage() {

        Toast.makeText(PhotoActivity.this, "share", Toast.LENGTH_SHORT).show();
    }

    private void downloadImage() {

        Toast.makeText(PhotoActivity.this, "downloaded", Toast.LENGTH_SHORT).show();
    }
    
    
}
