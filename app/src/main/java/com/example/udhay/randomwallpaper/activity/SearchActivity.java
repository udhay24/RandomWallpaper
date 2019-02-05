package com.example.udhay.randomwallpaper.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.adapters.SearchResultAdapter;
import com.example.udhay.randomwallpaper.api.UnSplashApi;
import com.example.udhay.randomwallpaper.contentprovider.RecentSearchContentProvider;
import com.example.udhay.randomwallpaper.interfaces.ClickInterface;
import com.example.udhay.randomwallpaper.listeners.EndlessScrollListener;
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

        SearchRecentSuggestions searchRecentSuggestions = new SearchRecentSuggestions(this
                , RecentSearchContentProvider.AUTHORITY, RecentSearchContentProvider.MODE);

        searchRecentSuggestions.saveRecentQuery(query, null);

        if (searchView != null) {
            searchView.setQuery(query, false);
        }
        loadUsers();

        loadCollection();

        loadWallpaper();


    }

    private void loadWallpaper() {

        GridLayoutManager wallpaperGridLayoutManager = new GridLayoutManager(SearchActivity.this, 2);

        wallpaperRecyclerView.setLayoutManager(wallpaperGridLayoutManager);

        unSplashApi.searchPhotos(query, 1, 30, null, null).enqueue(new Callback<PhotoSearchResult>() {
            @Override
            public void onResponse(Call<PhotoSearchResult> call, final Response<PhotoSearchResult> response) {

                wallpaperRecyclerView.setAdapter(new SearchResultAdapter().getWallpaperAdapter(response.body(), new ClickInterface() {
                    @Override
                    public void onClick(View view) {
                        int position = wallpaperRecyclerView.getChildAdapterPosition(view);
                        String id = response.body().getSearchPhotos().get(position).getId();

                        Intent intent = new Intent(SearchActivity.this, PhotoDetailActivity.class);
                        intent.putExtra(PhotoDetailActivity.ID, id);

                        startActivity(intent);
                    }
                }));
            }

            @Override
            public void onFailure(Call<PhotoSearchResult> call, Throwable t) {

            }
        });


        wallpaperRecyclerView.addOnScrollListener(new EndlessScrollListener(wallpaperGridLayoutManager) {

            private boolean load = false;

            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {

                unSplashApi.searchPhotos(query, page, 30, null, null).enqueue(new Callback<PhotoSearchResult>() {
                    @Override
                    public void onResponse(Call<PhotoSearchResult> call, Response<PhotoSearchResult> response) {

                        if (response.body() != null & response.errorBody() == null) {
                            ((SearchResultAdapter.WallpaperAdapter) wallpaperRecyclerView.getAdapter()).addPhotos(response.body().getSearchPhotos());
                            load = true;
                        } else {
                            load = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<PhotoSearchResult> call, Throwable t) {
                        load = false;
                    }
                });

                return load;
            }
        });

    }

    private void loadCollection() {

        unSplashApi.searchCollection(query, 1, 20).enqueue(new Callback<CollectionSearchResult>() {
            @Override
            public void onResponse(Call<CollectionSearchResult> call, final Response<CollectionSearchResult> response) {

                collectionRecyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.HORIZONTAL, false));

                collectionRecyclerView.setAdapter(new SearchResultAdapter().getCollectionsAdapter(response.body(), new ClickInterface() {
                    @Override
                    public void onClick(View view) {
                        int position = collectionRecyclerView.getChildAdapterPosition(view);
                        int id = response.body().getCollections().get(position).getId();
                        String title = response.body().getCollections().get(position).getTitle();

                        Intent intent = new Intent(SearchActivity.this, CollectionDetailActivity.class);
                        intent.putExtra(CollectionDetailActivity.COLLECTION_ID, id);
                        intent.putExtra(CollectionDetailActivity.COLLECTION_TITLE, title);

                        startActivity(intent);
                    }
                }));
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
