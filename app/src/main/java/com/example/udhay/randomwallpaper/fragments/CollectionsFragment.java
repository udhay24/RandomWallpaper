package com.example.udhay.randomwallpaper.fragments;


import android.content.Intent;
import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionsFragment extends Fragment {

    public static final String FRAGMENT_TAB_TITLE = "Collections";
    private static final String FRAGMENT_ACTION = "fragment_action";
    private static final String FRAGMENT_ACTION_PARAMETER = "fragment_action_parameter";
    private static Callback<List<Collection>> retrofitResultCallBack;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.preloader_1_GifView)
    GifImageView progressGifView;

    @BindView(R.id.error_image)
    ImageView errorImage;

    private CollectionsAdapter collectionsAdapter;
    private String actionParameter;
    private GridLayoutManager gridLayoutManager;
    private UnSplashApi unSplashApi;
    private ClickInterface collectionClickInterface;

    public static CollectionsFragment getCollectionSFragment(COLLECTIONS_FRAGMENT_ACTIONS fragmentActions, String action) {

        CollectionsFragment fragment = new CollectionsFragment();

        Bundle bundle = new Bundle();

        bundle.putString(FRAGMENT_ACTION, fragmentActions.toString());
        bundle.putString(FRAGMENT_ACTION_PARAMETER, action);

        fragment.setArguments(bundle);

        return fragment;
    }


    public CollectionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null)
            actionParameter = this.getArguments().getString(FRAGMENT_ACTION_PARAMETER);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collections, container, false);

        ButterKnife.bind(this, view);

        gridLayoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

        collectionClickInterface = new ClickInterface() {
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
        };

        retrofitResultCallBack = new Callback<List<Collection>>() {

            @Override
            public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                collectionsAdapter = new CollectionsAdapter(response.body(), collectionClickInterface);
                recyclerView.setAdapter(collectionsAdapter);
            }

            @Override
            public void onFailure(Call<List<Collection>> call, Throwable t) {

                displayError();
                Toast.makeText(CollectionsFragment.this.getContext(), "Unable to load collections", Toast.LENGTH_SHORT).show();
            }
        };


        displayLoading();

        if (this.getArguments() != null) {

            String action = this.getArguments().getString(FRAGMENT_ACTION);

            if (action == null)
                action = COLLECTIONS_FRAGMENT_ACTIONS.NEW_COLLECTIONS_DISPLAY.name();

            if (action.equals(COLLECTIONS_FRAGMENT_ACTIONS.NEW_COLLECTIONS_DISPLAY.name())) {

                displayNewCollection();

            } else if (action.equals(COLLECTIONS_FRAGMENT_ACTIONS.SEARCH_COLLECTIONS_DISPLAY.name())) {

                displaySearchCollections();

            }

        } else {
            Toast.makeText(CollectionsFragment.this.getContext(), "this is null", Toast.LENGTH_SHORT).show();
        }


        return view;
    }

    private void displayNewCollection() {

        unSplashApi.getCollections(1, 10).enqueue(retrofitResultCallBack);
        recyclerView.addOnScrollListener(new EndlessScrollListener(30, gridLayoutManager) {

            private boolean load = true;

            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {


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
        });
        displayCollections();
    }


    private void displaySearchCollections() {

//        unSplashApi.searchCollection(actionParameter , 1, 10).enqueue(retrofitResultCallBack);
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


    //Enum for the actions performed by this fragment
    public enum COLLECTIONS_FRAGMENT_ACTIONS {

        NEW_COLLECTIONS_DISPLAY, SEARCH_COLLECTIONS_DISPLAY
    }
}
