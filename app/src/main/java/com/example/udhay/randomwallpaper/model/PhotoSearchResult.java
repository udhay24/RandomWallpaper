package com.example.udhay.randomwallpaper.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoSearchResult {

    @SerializedName("total")
    int total;
    @SerializedName("total_pages")
    int totalPages;
    @SerializedName("results")
    List<SearchPhoto> searchPhotos;

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

    public List<SearchPhoto> getSearchPhotos() {
        return searchPhotos;
    }

    public void setSearchPhotos(List<SearchPhoto> searchPhotos) {
        this.searchPhotos = searchPhotos;
    }

    public class SearchPhoto extends Photo {

        @SerializedName("tags")
        List<Tag> tags;

        @SerializedName("photo_tags")
        List<Tag> photoTags;

        public class Tag {

            @SerializedName("title")
            String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

        }
    }
}
