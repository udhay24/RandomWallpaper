package com.example.udhay.randomwallpaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.udhay.randomwallpaper.Adapters.ImageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.image_list_view)
    ListView imageListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        imageListView.setAdapter(new ImageAdapter(this));
    }
}
