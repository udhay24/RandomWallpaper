package com.example.udhay.randomwallpaper.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.udhay.randomwallpaper.Adapters.FeaturedImageAdapter;
import com.example.udhay.randomwallpaper.Interfaces.ClickInterface;
import com.example.udhay.randomwallpaper.Listeners.EndlessScrollListener;
import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.Util.RetrofitClient;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.model.Photo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionDetailActivity extends AppCompatActivity {

    public static final String COLLECTION_ID = "collection_id";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
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

        id = getIntent().getIntExtra(COLLECTION_ID, 0);

        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        loadInitialImages();
        recyclerView.addOnScrollListener(getScrollListener());

    }

    private void loadInitialImages() {

        unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

        unSplashApi.getCollectionById(id, 1, 10).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                featuredImageAdapter = new FeaturedImageAdapter(response.body(), new ClickInterface() {
                    @Override
                    public void onClick(View view) {

                        int position = recyclerView.getChildAdapterPosition(view);

                        Intent intent = new Intent(CollectionDetailActivity.this, PhotoDetailActivity.class);

                        intent.putExtra(PhotoDetailActivity.ID, featuredImageAdapter.getList().get(position).getId());
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(featuredImageAdapter);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

                displayError();
                Toast.makeText(CollectionDetailActivity.this, "Unable to load Images", Toast.LENGTH_SHORT).show();
            }
        });

        displayImages();

    }


    private EndlessScrollListener getScrollListener() {

        EndlessScrollListener endlessScrollListener = new EndlessScrollListener(gridLayoutManager) {

            private boolean load = true;

            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

                unSplashApi.getCollectionById(id, page, 20).enqueue(new Callback<List<Photo>>() {
                    @Override
                    public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                        if (response.body() != null && response.errorBody() == null) {

                            List<Photo> photos = response.body();
                            featuredImageAdapter.addPhotoList(photos);
                            load = true;

                        } else {

                            displayError();
                            Toast.makeText(CollectionDetailActivity.this, "Error loading images", Toast.LENGTH_SHORT).show();
                            load = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Photo>> call, Throwable t) {

                        displayError();
                        Toast.makeText(CollectionDetailActivity.this, "Unable to load images", Toast.LENGTH_SHORT).show();
                        load = false;

                    }
                });

                return load;
            }
        };
        return endlessScrollListener;
    }


    private void displayError() {

    }

    private void displayImages() {

    }
}
