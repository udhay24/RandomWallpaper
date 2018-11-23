package com.example.udhay.randomwallpaper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class User {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("twitter_username")
    @Expose
    private String twitterUsername;
    @SerializedName("portfolio_url")
    @Expose
    private String portfolioUrl;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("links")
    @Expose
    private Links links;
    @SerializedName("profile_image")
    @Expose
    private ProfileImage profileImage;
    @SerializedName("instagram_username")
    @Expose
    private String instagramUsername;
    @SerializedName("total_collections")
    @Expose
    private Integer totalCollections;
    @SerializedName("total_likes")
    @Expose
    private Integer totalLikes;
    @SerializedName("total_photos")
    @Expose
    private Integer totalPhotos;
    @SerializedName("accepted_tos")
    @Expose
    private Boolean acceptedTos;
    @SerializedName("followed_by_user")
    @Expose
    private Boolean followedByUser;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public String getPortfolioUrl() {
        return portfolioUrl;
    }

    public void setPortfolioUrl(String portfolioUrl) {
        this.portfolioUrl = portfolioUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public String getInstagramUsername() {
        return instagramUsername;
    }

    public void setInstagramUsername(String instagramUsername) {
        this.instagramUsername = instagramUsername;
    }

    public Integer getTotalCollections() {
        return totalCollections;
    }

    public void setTotalCollections(Integer totalCollections) {
        this.totalCollections = totalCollections;
    }

    public Integer getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
    }

    public Integer getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(Integer totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    public Boolean getAcceptedTos() {
        return acceptedTos;
    }

    public void setAcceptedTos(Boolean acceptedTos) {
        this.acceptedTos = acceptedTos;
    }

    public Boolean getFollowedByUser() {
        return followedByUser;
    }

    public void setFollowedByUser(Boolean followedByUser) {
        this.followedByUser = followedByUser;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

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


    public static class Aggregated {

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

    public static class Badge {

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

    public static class Custom {

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

    public static class Links {

        @SerializedName("self")
        @Expose
        private String self;
        @SerializedName("html")
        @Expose
        private String html;
        @SerializedName("photos")
        @Expose
        private String photos;
        @SerializedName("likes")
        @Expose
        private String likes;
        @SerializedName("portfolio")
        @Expose
        private String portfolio;
        @SerializedName("following")
        @Expose
        private String following;
        @SerializedName("followers")
        @Expose
        private String followers;

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getPhotos() {
            return photos;
        }

        public void setPhotos(String photos) {
            this.photos = photos;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public String getPortfolio() {
            return portfolio;
        }

        public void setPortfolio(String portfolio) {
            this.portfolio = portfolio;
        }

        public String getFollowing() {
            return following;
        }

        public void setFollowing(String following) {
            this.following = following;
        }

        public String getFollowers() {
            return followers;
        }

        public void setFollowers(String followers) {
            this.followers = followers;
        }

    }

    public static class Photo_Links {

        @SerializedName("self")
        @Expose
        private String self;
        @SerializedName("html")
        @Expose
        private String html;
        @SerializedName("download")
        @Expose
        private String download;
        @SerializedName("download_location")
        @Expose
        private String downloadLocation;

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }

        public String getDownloadLocation() {
            return downloadLocation;
        }

        public void setDownloadLocation(String downloadLocation) {
            this.downloadLocation = downloadLocation;
        }

    }

    public static class Photo {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("color")
        @Expose
        private String color;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("urls")
        @Expose
        private Urls urls;
        @SerializedName("links")
        @Expose
        private Photo_Links links;
        @SerializedName("categories")
        @Expose
        private List<Object> categories = null;
        @SerializedName("sponsored")
        @Expose
        private Boolean sponsored;
        @SerializedName("sponsored_by")
        @Expose
        private Object sponsoredBy;
        @SerializedName("sponsored_impressions_id")
        @Expose
        private Object sponsoredImpressionsId;
        @SerializedName("likes")
        @Expose
        private Integer likes;
        @SerializedName("liked_by_user")
        @Expose
        private Boolean likedByUser;
        @SerializedName("current_user_collections")
        @Expose
        private List<Object> currentUserCollections = null;
        @SerializedName("slug")
        @Expose
        private Object slug;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Urls getUrls() {
            return urls;
        }

        public void setUrls(Urls urls) {
            this.urls = urls;
        }

        public Photo_Links getLinks() {
            return links;
        }

        public void setLinks(Photo_Links links) {
            this.links = links;
        }

        public List<Object> getCategories() {
            return categories;
        }

        public void setCategories(List<Object> categories) {
            this.categories = categories;
        }

        public Boolean getSponsored() {
            return sponsored;
        }

        public void setSponsored(Boolean sponsored) {
            this.sponsored = sponsored;
        }

        public Object getSponsoredBy() {
            return sponsoredBy;
        }

        public void setSponsoredBy(Object sponsoredBy) {
            this.sponsoredBy = sponsoredBy;
        }

        public Object getSponsoredImpressionsId() {
            return sponsoredImpressionsId;
        }

        public void setSponsoredImpressionsId(Object sponsoredImpressionsId) {
            this.sponsoredImpressionsId = sponsoredImpressionsId;
        }

        public Integer getLikes() {
            return likes;
        }

        public void setLikes(Integer likes) {
            this.likes = likes;
        }

        public Boolean getLikedByUser() {
            return likedByUser;
        }

        public void setLikedByUser(Boolean likedByUser) {
            this.likedByUser = likedByUser;
        }

        public List<Object> getCurrentUserCollections() {
            return currentUserCollections;
        }

        public void setCurrentUserCollections(List<Object> currentUserCollections) {
            this.currentUserCollections = currentUserCollections;
        }

        public Object getSlug() {
            return slug;
        }

        public void setSlug(Object slug) {
            this.slug = slug;
        }

    }

    public static class ProfileImage {

        @SerializedName("small")
        @Expose
        private String small;
        @SerializedName("medium")
        @Expose
        private String medium;
        @SerializedName("large")
        @Expose
        private String large;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

    }

    public static class Tags {

        @SerializedName("custom")
        @Expose
        private List<Custom> custom = null;
        @SerializedName("aggregated")
        @Expose
        private List<Aggregated> aggregated = null;

        public List<Custom> getCustom() {
            return custom;
        }

        public void setCustom(List<Custom> custom) {
            this.custom = custom;
        }

        public List<Aggregated> getAggregated() {
            return aggregated;
        }

        public void setAggregated(List<Aggregated> aggregated) {
            this.aggregated = aggregated;
        }

    }

    public static class Urls {

        @SerializedName("raw")
        @Expose
        private String raw;
        @SerializedName("full")
        @Expose
        private String full;
        @SerializedName("regular")
        @Expose
        private String regular;
        @SerializedName("small")
        @Expose
        private String small;
        @SerializedName("thumb")
        @Expose
        private String thumb;

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getFull() {
            return full;
        }

        public void setFull(String full) {
            this.full = full;
        }

        public String getRegular() {
            return regular;
        }

        public void setRegular(String regular) {
            this.regular = regular;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

    }
}
