package com.example.udhay.randomwallpaper.model;

import com.google.gson.annotations.SerializedName;

public class Photo {

    public String getDescription() {
        return description;
    }

    public Urls getUrls() {
        return urls;
    }

    @SerializedName("description")
    private String description;

    @SerializedName("urls")
    private Urls urls;

    public static class Urls {
        /*"urls": {
            "raw": "https://images.unsplash.com/photo-1534176707315-93da3e13086a?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjQxMzI4fQ&s=34f2990a1364871e9a98c6d3da089812",
                    "full": "https://images.unsplash.com/photo-1534176707315-93da3e13086a?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjQxMzI4fQ&s=9b7e10c162105d8fe90412df1a38dc08",
                    "regular": "https://images.unsplash.com/photo-1534176707315-93da3e13086a?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjQxMzI4fQ&s=964c031efeaca31f32376c73c3184f55",
                    "small": "https://images.unsplash.com/photo-1534176707315-93da3e13086a?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjQxMzI4fQ&s=74c53bee2425169335024fa9f307547b",
                    "thumb": "https://images.unsplash.com/photo-1534176707315-93da3e13086a?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjQxMzI4fQ&s=8e99ed017bb173105576986886d1b4c2"
        },*/
        @SerializedName("raw")
        String raw;

        public String getRaw() {
            return raw;
        }

        public String getFull() {
            return full;
        }

        public String getRegular() {
            return regular;
        }

        public String getSmall() {
            return small;
        }

        public String getThumb() {
            return thumb;
        }

        @SerializedName("full")
        String full;

        @SerializedName("regular")
        String regular;

        @SerializedName("small")
        String small;

        @SerializedName("thumb")
        String thumb;
    }
}
