package com.example.udhay.randomwallpaper.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollectionSearchResult {

    @SerializedName("total")
    int total;

    @SerializedName("total_pages")
    int totalPages;
    @SerializedName("results")
    List<Collection> collections;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

}
