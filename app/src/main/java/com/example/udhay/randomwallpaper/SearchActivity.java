package com.example.udhay.randomwallpaper;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.udhay.randomwallpaper.Adapters.SearchResultAdapter;
import com.example.udhay.randomwallpaper.Util.RetrofitClient;
import com.example.udhay.randomwallpaper.api.PhotoApi;
import com.example.udhay.randomwallpaper.model.PhotoSearchResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class SearchActivity extends AppCompatActivity {

    String query;

    SearchView searchView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.artist_text_view)
    TextView textView;
    @BindView(R.id.artist_recycler_view)
    RecyclerView artistRecyclerView;
    @BindView(R.id.collection_text_view)
    TextView collectionTextView;
    @BindView(R.id.collection_recycler_view)
    RecyclerView collectionRecyclerView;
    @BindView(R.id.wallpaper_text_view)
    TextView wallpaperTextView;
    @BindView(R.id.wallpaper_recycler_view)
    RecyclerView wallpaperRecyclerView;


    PhotoApi photoApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        photoApi = RetrofitClient.getClient().create(PhotoApi.class);

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
        loadWallpaper();
    }

    private void loadWallpaper() {


        photoApi.searchPhotos(query, 1, 30, null, null).enqueue(new Callback<PhotoSearchResult>() {
            @Override
            public void onResponse(Call<PhotoSearchResult> call, Response<PhotoSearchResult> response) {

                Timber.v("response " + response.body().getSearchPhotos().get(0).getUrls().getFull());

                wallpaperRecyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2));

                wallpaperRecyclerView.setAdapter(new SearchResultAdapter().getWallpaperAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<PhotoSearchResult> call, Throwable t) {

            }
        });
    }

}
