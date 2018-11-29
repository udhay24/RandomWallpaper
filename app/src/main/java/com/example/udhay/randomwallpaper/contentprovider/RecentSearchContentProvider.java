package com.example.udhay.randomwallpaper.contentprovider;

import android.content.SearchRecentSuggestionsProvider;

public class RecentSearchContentProvider extends SearchRecentSuggestionsProvider {

    public static final String AUTHORITY = "com.example.udhay.randomwallpaper.contentprovider.RecentSearchContentProvider";

    public static final int MODE = DATABASE_MODE_QUERIES;

    public RecentSearchContentProvider() {

        setupSuggestions(AUTHORITY, MODE);
    }

}
