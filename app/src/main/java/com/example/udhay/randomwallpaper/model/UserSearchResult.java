package com.example.udhay.randomwallpaper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserSearchResult {


    @SerializedName("total")
    @Expose
    int total;

    @SerializedName("total_pages")
    @Expose
    int totalPages;

    @SerializedName("results")
    @Expose
    List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public void setusers(List<User> users) {
        this.users = users;
    }


}

