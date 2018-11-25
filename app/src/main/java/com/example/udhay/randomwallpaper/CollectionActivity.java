package com.example.udhay.randomwallpaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.udhay.randomwallpaper.Adapters.FeaturedImageAdapter;
import com.example.udhay.randomwallpaper.Util.GifImageView;
import com.example.udhay.randomwallpaper.api.UnSplashApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class CollectionActivity extends AppCompatActivity {


    //UI Components
    @BindView(R.id.toolbar)
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ImageView errorImage;
    private FeaturedImageAdapter featuredImageAdapter;
    private GridLayoutManager gridLayoutManager;


    //Global Data
    UnSplashApi unSplashApi;


    //onCreate Functions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);


        //Setting up the Default setting and Toolbar
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        //Setting Views
        recyclerView = findViewById(R.id.featured_images_recycler_view);
        recyclerView.setLayoutManager(gridLayoutManager);
        errorImage = findViewById(R.id.error_image);


        //Setting up the RecyclerView
        gridLayoutManager = new GridLayoutManager(CollectionActivity.this, 2);


    }
}
