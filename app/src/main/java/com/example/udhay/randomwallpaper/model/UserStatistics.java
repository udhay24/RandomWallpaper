package com.example.udhay.randomwallpaper.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class UserStatistics {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("downloads")
    @Expose
    private Downloads downloads;
    @SerializedName("views")
    @Expose
    private Views views;
    @SerializedName("likes")
    @Expose
    private Likes likes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Downloads getDownloads() {
        return downloads;
    }

    public void setDownloads(Downloads downloads) {
        this.downloads = downloads;
    }

    public Views getViews() {
        return views;
    }

    public void setViews(Views views) {
        this.views = views;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }


    public static class Downloads {

        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("historical")
        @Expose
        private Historical historical;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Historical getHistorical() {
            return historical;
        }

        public void setHistorical(Historical historical) {
            this.historical = historical;
        }

    }

    public static class Likes {

        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("historical")
        @Expose
        private Historical historical;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Historical getHistorical() {
            return historical;
        }

        public void setHistorical(Historical historical) {
            this.historical = historical;
        }

    }

    public static class Views {

        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("historical")
        @Expose
        private Historical historical;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Historical getHistorical() {
            return historical;
        }

        public void setHistorical(Historical historical) {
            this.historical = historical;
        }

    }

    public static class Historical {

        @SerializedName("change")
        @Expose
        private Integer change;
        @SerializedName("average")
        @Expose
        private Integer average;
        @SerializedName("resolution")
        @Expose
        private String resolution;
        @SerializedName("quantity")
        @Expose
        private Integer quantity;
        @SerializedName("values")
        @Expose
        private List<Value> values = null;

        public Integer getChange() {
            return change;
        }

        public void setChange(Integer change) {
            this.change = change;
        }

        public Integer getAverage() {
            return average;
        }

        public void setAverage(Integer average) {
            this.average = average;
        }

        public String getResolution() {
            return resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public List<Value> getValues() {
            return values;
        }

        public void setValues(List<Value> values) {
            this.values = values;
        }

    }


    public static class Value {

        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("value")
        @Expose
        private Integer value;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

    }


}
