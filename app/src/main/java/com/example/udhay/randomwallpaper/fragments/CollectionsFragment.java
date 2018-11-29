package com.example.udhay.randomwallpaper.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.activity.CollectionDetailActivity;
import com.example.udhay.randomwallpaper.adapters.CollectionsAdapter;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.interfaces.ClickInterface;
import com.example.udhay.randomwallpaper.listeners.EndlessScrollListener;
import com.example.udhay.randomwallpaper.model.Collection;
import com.example.udhay.randomwallpaper.util.GifImageView;
import com.example.udhay.randomwallpaper.util.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionsFragment extends Fragment {

    public static final String FRAGMENT_TITLE = "Collections";
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    CollectionsAdapter collectionsAdapter;

    UnSplashApi unSplashApi;

    GifImageView progressGifView;
    ImageView errorImage;

    public CollectionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collections, container, false);
        recyclerView = view.findViewById(R.id.collections_recycler_view);
        gridLayoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        progressGifView = view.findViewById(R.id.preloader_1_GifView);
        errorImage = view.findViewById(R.id.error_image);

        displayLoading();

        loadInitialCollections();

        recyclerView.addOnScrollListener(getScrollListener());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private EndlessScrollListener getScrollListener() {


        EndlessScrollListener endlessScrollListener = new EndlessScrollListener(30, gridLayoutManager) {

            private boolean load = true;

            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {

                unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

                unSplashApi.getCollections(page, 30).enqueue(new Callback<List<Collection>>() {
                    @Override
                    public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {

                        if (response.body() != null && response.errorBody() == null) {

                            collectionsAdapter.addCollection(response.body());
                            load = true;
                        } else {

                            displayError();
                            Toast.makeText(CollectionsFragment.this.getContext(), "Error loading collections", Toast.LENGTH_SHORT).show();
                            load = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Collection>> call, Throwable t) {

                        displayError();
                        Toast.makeText(CollectionsFragment.this.getContext(), "Unable to load collections", Toast.LENGTH_SHORT).show();
                        load = false;
                    }
                });
                return load;
            }
        };
        return endlessScrollListener;
    }

    private void loadInitialCollections() {

        unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

        unSplashApi.getCollections(1, 10).enqueue(new Callback<List<Collection>>() {

            @Override
            public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                collectionsAdapter = new CollectionsAdapter(response.body(), new ClickInterface() {
                    @Override
                    public void onClick(View view) {

                        int position = recyclerView.getChildAdapterPosition(view);
                        int id = collectionsAdapter.getCollections().get(position).getId();
                        String title = collectionsAdapter.getCollections().get(position).getTitle();

                        Intent intent = new Intent(CollectionsFragment.this.getContext(), CollectionDetailActivity.class);
                        intent.putExtra(CollectionDetailActivity.COLLECTION_ID, id);
                        intent.putExtra(CollectionDetailActivity.COLLECTION_TITLE, title);
                        startActivity(intent);

                    }
                });
                recyclerView.setAdapter(collectionsAdapter);
            }

            @Override
            public void onFailure(Call<List<Collection>> call, Throwable t) {

                displayError();
                Toast.makeText(CollectionsFragment.this.getContext(), "Unable to load collections", Toast.LENGTH_SHORT).show();
            }
        });

        displayCollections();
    }

    private void displayError() {

        recyclerView.setVisibility(View.GONE);
        progressGifView.setVisibility(View.GONE);
        errorImage.setVisibility(View.VISIBLE);
    }

    private void displayCollections() {

        recyclerView.setVisibility(View.VISIBLE);
        progressGifView.setVisibility(View.GONE);
        errorImage.setVisibility(View.GONE);
    }

    private void displayLoading() {

        progressGifView.setGifImageResource(R.drawable.preloader_1);

        recyclerView.setVisibility(View.GONE);
        progressGifView.setVisibility(View.VISIBLE);
        errorImage.setVisibility(View.GONE);
    }


}
