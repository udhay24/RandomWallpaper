package com.example.udhay.randomwallpaper.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.adapters.SearchResultAdapter;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.model.CollectionSearchResult;
import com.example.udhay.randomwallpaper.model.PhotoSearchResult;
import com.example.udhay.randomwallpaper.model.UserSearchResult;
import com.example.udhay.randomwallpaper.util.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    String query;

    SearchView searchView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.user_text_view)
    TextView userTextView;
    @BindView(R.id.user_recycler_view)
    RecyclerView userRecyclerView;
    @BindView(R.id.collection_text_view)
    TextView collectionTextView;
    @BindView(R.id.collection_recycler_view)
    RecyclerView collectionRecyclerView;
    @BindView(R.id.wallpaper_text_view)
    TextView wallpaperTextView;
    @BindView(R.id.wallpaper_recycler_view)
    RecyclerView wallpaperRecyclerView;


    UnSplashApi unSplashApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        unSplashApi = RetrofitClient.getClient().create(UnSplashApi.class);

        setSupportActionBar(toolbar);


    }

    @Override
    protected void onStart() {
        super.onStart();
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_activity_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search_menu_item);
        searchView = (SearchView) menuItem.getActionView();
        menuItem.expandActionView();

        menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;//to keep the action view open
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                finish();
                return false; // To prevent the action view from closing
            }
        });

        searchView.setQuery(query, true);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        searchView.setQueryHint("Search Images...");

        return true;
    }


    private void handleIntent(Intent intent) {

        query = intent.getStringExtra(SearchManager.QUERY);

        if (searchView != null) {
            searchView.setQuery(query, false);
        }
        loadUsers();

        loadCollection();

        loadWallpaper();


    }

    private void loadWallpaper() {


        unSplashApi.searchPhotos(query, 1, 30, null, null).enqueue(new Callback<PhotoSearchResult>() {
            @Override
            public void onResponse(Call<PhotoSearchResult> call, Response<PhotoSearchResult> response) {

                wallpaperRecyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2));

                wallpaperRecyclerView.setAdapter(new SearchResultAdapter().getWallpaperAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<PhotoSearchResult> call, Throwable t) {

            }
        });
    }

    private void loadCollection() {

        unSplashApi.searchCollection(query, 1, 20).enqueue(new Callback<CollectionSearchResult>() {
            @Override
            public void onResponse(Call<CollectionSearchResult> call, Response<CollectionSearchResult> response) {

                collectionRecyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.HORIZONTAL, false));

                collectionRecyclerView.setAdapter(new SearchResultAdapter().getCollectionsAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<CollectionSearchResult> call, Throwable t) {

            }
        });
    }

    private void loadUsers() {

        userRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        unSplashApi.searchUser(query, 1, 10).enqueue(new Callback<UserSearchResult>() {
            @Override
            public void onResponse(Call<UserSearchResult> call, Response<UserSearchResult> response) {
                userRecyclerView.setAdapter(new SearchResultAdapter().getUserAdapter(response.body()));
                userRecyclerView.setItemAnimator(new OvershootInLeftAnimator());
            }

            @Override
            public void onFailure(Call<UserSearchResult> call, Throwable t) {

            }
        });
    }
}
