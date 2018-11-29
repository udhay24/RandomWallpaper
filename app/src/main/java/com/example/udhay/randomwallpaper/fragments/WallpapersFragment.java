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
import com.example.udhay.randomwallpaper.activity.PhotoDetailActivity;
import com.example.udhay.randomwallpaper.adapters.FeaturedImageAdapter;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.interfaces.ClickInterface;
import com.example.udhay.randomwallpaper.listeners.EndlessScrollListener;
import com.example.udhay.randomwallpaper.model.Photo;
import com.example.udhay.randomwallpaper.util.GifImageView;
import com.example.udhay.randomwallpaper.util.RetrofitClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WallpapersFragment extends Fragment {

    public static final String FRAGMENT_TAB_TITLE = "NEW";
    private static final String FRAGMENT_ACTION = "fragment_action";
    private static final String FRAGMENT_ACTION_PARAMETER = "fragment_action_parameter";

    private static EndlessScrollListener endlessScrollListener;

    @BindView(R.id.preloader_1_GifView)
    GifImageView progressGifView;

    @BindView(R.id.error_image)
    ImageView errorImage;

    @BindView(R.id.featured_images_recycler_view)
    RecyclerView recyclerView;

    private String actionParameter;
    private static Callback<List<Photo>> retrofitResultCallBack;
    private FeaturedImageAdapter featuredImageAdapter;
    private GridLayoutManager gridLayoutManager;
    private UnSplashApi unSplashApi;
    private ClickInterface wallpaperClickInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null)
            actionParameter = this.getArguments().getString(FRAGMENT_ACTION_PARAMETER);

        // Inflate the layout for this fragment
        View layoutView = inflater.inflate(R.layout.fragment_wallpaper, container, false);

        ButterKnife.bind(this, layoutView);

        gridLayoutManager = new GridLayoutManager(this.getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);


        unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

        wallpaperClickInterface = new ClickInterface() {
            @Override
            public void onClick(View view) {

                int position = recyclerView.getChildAdapterPosition(view);

                Intent intent = new Intent(WallpapersFragment.this.getContext(), PhotoDetailActivity.class);

                intent.putExtra(PhotoDetailActivity.ID, featuredImageAdapter.getList().get(position).getId());
                startActivity(intent);
            }
        };

        retrofitResultCallBack = new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                featuredImageAdapter = new FeaturedImageAdapter(response.body(), wallpaperClickInterface);
                recyclerView.setAdapter(featuredImageAdapter);
                recyclerView.addOnScrollListener(endlessScrollListener);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

                displayError();
                Toast.makeText(WallpapersFragment.this.getContext(), "Unable to load Images", Toast.LENGTH_SHORT).show();
            }
        };


        endlessScrollListener = getScrollListener();
        displayLoading();

        if (this.getArguments() != null) {

            String action = this.getArguments().getString(FRAGMENT_ACTION);

            if (action == null) action = "";

            if (action.equals(WALLPAPERS_FRAGMENT_ACTIONS.COLLECTION_WALLPAPER_DISPLAY.name())) {

                displayCollectionImages();

            } else if (action.equals(WALLPAPERS_FRAGMENT_ACTIONS.NEW_WALLPAPER_DISPLAY.name())) {

                displayFeaturedImages();

            } else if (action.equals(WALLPAPERS_FRAGMENT_ACTIONS.SEARCH_WALLPAPER_DISPLAY.name())) {

                displaySearchImages();

            }

        } else {
            Toast.makeText(WallpapersFragment.this.getContext(), "this is null", Toast.LENGTH_SHORT).show();
        }

        return layoutView;
    }

    public static WallpapersFragment getWallPaperSFragment(WALLPAPERS_FRAGMENT_ACTIONS fragmentActions, String action) {

        WallpapersFragment fragment = new WallpapersFragment();

        Bundle bundle = new Bundle();

        bundle.putString(FRAGMENT_ACTION, fragmentActions.toString());
        bundle.putString(FRAGMENT_ACTION_PARAMETER, action);

        fragment.setArguments(bundle);

        return fragment;
    }


    public WallpapersFragment() {
        // Required empty public constructor
    }

    private void displaySearchImages() {

        unSplashApi.searchCollection(actionParameter, 1, 10);
        displayImages();
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private EndlessScrollListener getScrollListener() {

        EndlessScrollListener endlessScrollListener = new EndlessScrollListener(gridLayoutManager) {

            private boolean load = true;

            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {

                unSplashApi.getPhotos(page, 20).enqueue(new Callback<List<Photo>>() {
                    @Override
                    public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                        if (response.body() != null && response.errorBody() == null) {

                            List<Photo> photos = response.body();
                            featuredImageAdapter.addPhotoList(photos);
                            load = true;

                        } else {

                            displayError();
                            Toast.makeText(WallpapersFragment.this.getContext(), "Error loading images", Toast.LENGTH_SHORT).show();
                            load = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Photo>> call, Throwable t) {

                        displayError();
                        Toast.makeText(WallpapersFragment.this.getContext(), "Unable to load images", Toast.LENGTH_SHORT).show();
                        load = false;

                    }
                });

                return load;
            }
        };
        return endlessScrollListener;
    }

    private void displayError() {

        recyclerView.setVisibility(View.GONE);
        progressGifView.setVisibility(View.GONE);
        errorImage.setVisibility(View.VISIBLE);
    }

    private void displayImages() {

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

    private void displayCollectionImages() {

        unSplashApi.getCollectionById(Integer.getInteger(actionParameter), 1, 10);
        displayImages();
    }

    private void displayFeaturedImages() {


        unSplashApi.getPhotos(1, 10).enqueue(retrofitResultCallBack);
        displayImages();

    }
    //Enum for the actions performed by this fragment
    public enum WALLPAPERS_FRAGMENT_ACTIONS {

        NEW_WALLPAPER_DISPLAY, SEARCH_WALLPAPER_DISPLAY, COLLECTION_WALLPAPER_DISPLAY
    }
}
