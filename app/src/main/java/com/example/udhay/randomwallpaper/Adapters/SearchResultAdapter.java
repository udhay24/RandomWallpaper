package com.example.udhay.randomwallpaper.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.model.CollectionSearchResult;
import com.example.udhay.randomwallpaper.model.PhotoSearchResult;
import com.example.udhay.randomwallpaper.model.UserSearchResult;
import com.squareup.picasso.Picasso;

import java.util.List;

import timber.log.Timber;

public class SearchResultAdapter {

    public WallpaperAdapter getWallpaperAdapter(PhotoSearchResult result) {
        return new WallpaperAdapter(result);
    }


    public CollectionsAdapter getCollectionsAdapter(CollectionSearchResult result) {
        return new CollectionsAdapter(result);
    }

    public UserAdapter getUserAdapter(UserSearchResult result) {
        return new UserAdapter(result);
    }

    class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.PhotoViewHolder> {

        private PhotoSearchResult photoSearchResult;

        public WallpaperAdapter(PhotoSearchResult list) {

            photoSearchResult = list;

        }

        @NonNull
        @Override
        public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.featured_image_view_holder, viewGroup, false);

            return new PhotoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PhotoViewHolder photoViewHolder, int i) {

            Picasso.get()
                    .load(photoSearchResult.getSearchPhotos().get(i).getUrls().getRegular())
                    .placeholder(R.drawable.ic_sand_clock)
                    .fit()
                    .centerCrop()
                    .into(photoViewHolder.imageView);

        }

        @Override
        public int getItemCount() {

            Timber.v("response count" + photoSearchResult.getSearchPhotos().size());

            return photoSearchResult.getSearchPhotos().size();
        }

        class PhotoViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;

            public PhotoViewHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.imageView);
            }
        }

    }


    class CollectionsAdapter extends com.example.udhay.randomwallpaper.Adapters.CollectionsAdapter {

        CollectionSearchResult searchResult;

        CollectionsAdapter(CollectionSearchResult result) {
            super(result.getCollections());
            searchResult = result;
        }

        @Override
        public void onBindViewHolder(@NonNull CollectionViewHolder collectionViewHolder, int i) {
            super.onBindViewHolder(collectionViewHolder, i);


            Picasso.get()
                    .load(super.collections.get(i).getCoverPhoto().getUrls().getRegular())
                    .into(collectionViewHolder.collectionImage);
        }

        @NonNull
        @Override
        public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            CollectionViewHolder collectionViewHolder = super.onCreateViewHolder(viewGroup, i);

            ViewGroup.LayoutParams params = collectionViewHolder.collectionFrameLayout.getLayoutParams();
            params.height = 400;
            params.width = 400;
            collectionViewHolder.collectionFrameLayout.requestLayout();

            params = collectionViewHolder.collectionCardView.getLayoutParams();
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            collectionViewHolder.collectionCardView.requestLayout();

            collectionViewHolder.collectionImage.setAlpha(0.7f);

            return collectionViewHolder;
        }
    }


    class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

        List<UserSearchResult.SearchUser> userList;

        public UserAdapter(UserSearchResult searchResult) {

            userList = searchResult.getSearchUsers();
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_result, viewGroup, false);

            return new UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {

            String name = userList.get(i).getFirstName();

            String url = userList.get(i).getProfileImage().getLarge();

            userViewHolder.textView.setText(name);
            Picasso.get()
                    .load(url)
                    .fit()
                    .centerCrop()
                    .into(userViewHolder.imageView);
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }

        class UserViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView textView;

            public UserViewHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.photoImageView);
                textView = view.findViewById(R.id.nameTextView);
            }
        }
    }

}
