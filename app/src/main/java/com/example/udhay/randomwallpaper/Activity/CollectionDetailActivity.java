package com.example.udhay.randomwallpaper.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.udhay.randomwallpaper.Fragments.FeaturedImages;
import com.example.udhay.randomwallpaper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionDetailActivity extends AppCompatActivity {

    public static final String COLLECTION_ID = "collection_id";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .add(new FeaturedImages(), FeaturedImages.FRAGMENT_TITLE)
                .commit();
        Toast.makeText(this, getIntent().getIntExtra(COLLECTION_ID, 0) + "", Toast.LENGTH_SHORT).show();
    }
}
