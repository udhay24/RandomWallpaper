package com.example.udhay.randomwallpaper.model;

import com.google.gson.annotations.SerializedName;

public class Photo {

    /*
    * {
    "id": "XgxcGUATna4",
    "created_at": "2018-08-13T12:13:50-04:00",
    "updated_at": "2018-08-28T21:20:44-04:00",
    "width": 3840,
    "height": 2160,
    "color": "#B5E1FA",
    "description": "brown cottage beside beachline",

    "links": {
        "self": "https://api.unsplash.com/photos/XgxcGUATna4",
        "html": "https://unsplash.com/photos/XgxcGUATna4",
        "download": "https://unsplash.com/photos/XgxcGUATna4/download",
        "download_location": "https://api.unsplash.com/photos/XgxcGUATna4/download"
    },
    "categories": [],
    "sponsored": false,
    "sponsored_by": null,
    "sponsored_impressions_id": null,
    "likes": 2,
    "liked_by_user": false,
    "current_user_collections": [],
    "slug": null,
    "user": {
        "id": "DeJ5HWVyajw",
        "updated_at": "2018-11-02T03:32:16-04:00",
        "username": "leowieling",
        "name": "Leo Wieling",
        "first_name": "Leo",
        "last_name": "Wieling",
        "twitter_username": "leowieling",
        "portfolio_url": "https://www.instagram.com/leowieling/",
        "bio": "Dutch | Proud father of 2 boys | Sales Pro | Music, Movie, Photography & Tech lover |  | Enjoying life | Drummer | All photos by me | Sony Alpha 6000 | iPhone | DJI Mavic Pro drone",
        "location": "Apeldoorn, the Netherlands",
        "links": {
            "self": "https://api.unsplash.com/users/leowieling",
            "html": "https://unsplash.com/@leowieling",
            "photos": "https://api.unsplash.com/users/leowieling/photos",
            "likes": "https://api.unsplash.com/users/leowieling/likes",
            "portfolio": "https://api.unsplash.com/users/leowieling/portfolio",
            "following": "https://api.unsplash.com/users/leowieling/following",
            "followers": "https://api.unsplash.com/users/leowieling/followers"
        },
        "profile_image": {
            "small": "https://images.unsplash.com/profile-1540414828498-7b5dfd95473f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=b4a11be372951b7943c80176279146db",
            "medium": "https://images.unsplash.com/profile-1540414828498-7b5dfd95473f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=96d8be9ebdbe8e545609c1968409b674",
            "large": "https://images.unsplash.com/profile-1540414828498-7b5dfd95473f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=dbbd87acacca433097c95fe226776134"
        },
        "instagram_username": "leowieling",
        "total_collections": 0,
        "total_likes": 39,
        "total_photos": 115,
        "accepted_tos": false
    },
    "exif": {
        "make": "DJI",
        "model": "FC220",
        "exposure_time": "1/365",
        "aperture": "2.2",
        "focal_length": "4.7",
        "iso": 100
    },
    "location": {
        "title": "Reiderwolder Polderdijk, Drieborg, Netherlands",
        "name": "Reiderwolder Polderdijk, 9688 Drieborg, Netherlands",
        "city": "Drieborg",
        "country": "Netherlands",
        "position": {
            "latitude": 53.23674722,
            "longitude": 7.20691389
        }
    },
    "views": 28855,
    "downloads": 96
}*/


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
