package com.example.udhay.randomwallpaper.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.appcompat.widget.Toolbar;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.adapters.FeaturedImageAdapter;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.fragments.WallpapersFragment;
import com.example.udhay.randomwallpaper.util.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionDetailActivity extends AppCompatActivity {

    public static final String COLLECTION_ID = "collection_id";
    public static final String COLLECTION_TITLE = "collectionTitle";

    UnSplashApi unSplashApi;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    FeaturedImageAdapter featuredImageAdapter;
    GridLayoutManager gridLayoutManager;

    private int id;
    private String collectionTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent() != null) {
            id = getIntent().getIntExtra(COLLECTION_ID, 0);
            collectionTitle = getIntent().getStringExtra(COLLECTION_TITLE);
        }
        getSupportActionBar().setTitle(collectionTitle);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_view, WallpapersFragment.getWallPaperSFragment(
                        WallpapersFragment.WALLPAPERS_FRAGMENT_ACTIONS.COLLECTION_WALLPAPER_DISPLAY, id
                ), WallpapersFragment.FRAGMENT_TAG).commit();
    }

}
