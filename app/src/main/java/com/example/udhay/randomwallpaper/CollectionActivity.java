package com.example.udhay.randomwallpaper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.udhay.randomwallpaper.Adapters.FeaturedImageAdapter;
import com.example.udhay.randomwallpaper.Util.RetrofitClient;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.model.Photo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionActivity extends AppCompatActivity {

    public static final String _ID = "id";

    //UI Components
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.collection_recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.error_image)
    public ImageView errorImage;


    private FeaturedImageAdapter featuredImageAdapter;
    private GridLayoutManager gridLayoutManager;


    //Global Data
    UnSplashApi unSplashApi;

    int collectionId;

    //onCreate Functions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);


        //Setting up the Default setting and Toolbar
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        //Setting up the RecyclerView
        gridLayoutManager = new GridLayoutManager(CollectionActivity.this, 2);


        //Setting Views
        recyclerView.setLayoutManager(gridLayoutManager);


        collectionId = getIntent().getIntExtra(_ID, 0);
        unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

        unSplashApi.getCollectionPhotos(collectionId, 1, 20).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                recyclerView.setAdapter(new FeaturedImageAdapter(response.body()));

            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });
    }
}
