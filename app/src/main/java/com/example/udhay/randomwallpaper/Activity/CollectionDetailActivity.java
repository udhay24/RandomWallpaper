package com.example.udhay.randomwallpaper.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.udhay.randomwallpaper.R;

public class CollectionDetailActivity extends AppCompatActivity {

    public static final String COLLECTION_ID = "collection_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_detail);

        Toast.makeText(this, getIntent().getIntExtra(COLLECTION_ID, 0) + "", Toast.LENGTH_SHORT).show();
    }
}
