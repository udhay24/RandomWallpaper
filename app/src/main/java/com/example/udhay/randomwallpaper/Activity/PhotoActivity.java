package com.example.udhay.randomwallpaper.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.udhay.randomwallpaper.R;
import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        url = getIntent().getStringExtra("url");

        ImageView imageView = findViewById(R.id.image);

        Picasso.get().load(url).into(imageView);
    }
}
