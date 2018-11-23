package com.example.udhay.randomwallpaper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserSearchResult {


    @SerializedName("total")
    @Expose
    int total;

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

    public List<SearchUser> getSearchUsers() {
        return searchUsers;
    }

    public void setSearchUsers(List<SearchUser> searchUsers) {
        this.searchUsers = searchUsers;
    }

    @SerializedName("total_pages")
    @Expose
    int totalPages;

    @SerializedName("results")
    @Expose
    List<SearchUser> searchUsers;


    public class SearchUser extends User {

        @SerializedName("badge")
        @Expose
        private Badge badge;
        @SerializedName("downloads")
        @Expose
        private Integer downloads;
        @SerializedName("tags")
        @Expose
        private Tags tags;
        @SerializedName("followers_count")
        @Expose
        private Integer followersCount;
        @SerializedName("following_count")
        @Expose
        private Integer followingCount;
        @SerializedName("allow_messages")
        @Expose
        private Boolean allowMessages;
        @SerializedName("numeric_id")
        @Expose
        private Integer numericId;


        public Badge getBadge() {
            return badge;
        }

        public void setBadge(Badge badge) {
            this.badge = badge;
        }

        public Integer getDownloads() {
            return downloads;
        }

        public void setDownloads(Integer downloads) {
            this.downloads = downloads;
        }

        public Tags getTags() {
            return tags;
        }

        public void setTags(Tags tags) {
            this.tags = tags;
        }

        public Integer getFollowersCount() {
            return followersCount;
        }

        public void setFollowersCount(Integer followersCount) {
            this.followersCount = followersCount;
        }

        public Integer getFollowingCount() {
            return followingCount;
        }

        public void setFollowingCount(Integer followingCount) {
            this.followingCount = followingCount;
        }

        public Boolean getAllowMessages() {
            return allowMessages;
        }

        public void setAllowMessages(Boolean allowMessages) {
            this.allowMessages = allowMessages;
        }

        public Integer getNumericId() {
            return numericId;
        }

        public void setNumericId(Integer numericId) {
            this.numericId = numericId;
        }

        public class Badge {

            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("primary")
            @Expose
            private Boolean primary;
            @SerializedName("slug")
            @Expose
            private String slug;
            @SerializedName("link")
            @Expose
            private String link;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Boolean getPrimary() {
                return primary;
            }

            public void setPrimary(Boolean primary) {
                this.primary = primary;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

        }

        public class Tags {

            @SerializedName("custom")
            @Expose
            private List<User.Custom> custom = null;
            @SerializedName("aggregated")
            @Expose
            private List<Aggregated> aggregated = null;

            public List<User.Custom> getCustom() {
                return custom;
            }

            public void setCustom(List<User.Custom> custom) {
                this.custom = custom;
            }

            public List<Aggregated> getAggregated() {
                return aggregated;
            }

            public void setAggregated(List<Aggregated> aggregated) {
                this.aggregated = aggregated;
            }

        }

        public class Aggregated {

            @SerializedName("title")
            @Expose
            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

        }
    }
}
