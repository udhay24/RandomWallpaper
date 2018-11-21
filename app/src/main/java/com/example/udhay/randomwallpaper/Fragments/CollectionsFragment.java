package com.example.udhay.randomwallpaper.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.udhay.randomwallpaper.Adapters.CollectionsAdapter;
import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.Util.RetrofitClient;
import com.example.udhay.randomwallpaper.api.PhotoApi;
import com.example.udhay.randomwallpaper.model.Collection;

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

    public CollectionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collections, container, false);
        recyclerView = view.findViewById(R.id.collections_recycler_view);
        gridLayoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        PhotoApi photoApi = RetrofitClient.getClient().create(PhotoApi.class);
        photoApi.getCollections(1, 10).enqueue(new Callback<List<Collection>>() {
            @Override
            public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                collectionsAdapter = new CollectionsAdapter(response.body());
                recyclerView.setAdapter(collectionsAdapter);
            }

            @Override
            public void onFailure(Call<List<Collection>> call, Throwable t) {
                Toast.makeText(CollectionsFragment.this.getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
