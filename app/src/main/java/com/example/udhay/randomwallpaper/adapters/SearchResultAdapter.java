package com.example.udhay.randomwallpaper.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.interfaces.ClickInterface;
import com.example.udhay.randomwallpaper.model.CollectionSearchResult;
import com.example.udhay.randomwallpaper.model.PhotoSearchResult;
import com.example.udhay.randomwallpaper.model.UserSearchResult;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchResultAdapter {

    public WallpaperAdapter getWallpaperAdapter(PhotoSearchResult result, ClickInterface clickInterface) {
        return new WallpaperAdapter(result, clickInterface);
    }


    public CollectionsAdapter getCollectionsAdapter(CollectionSearchResult result, ClickInterface clickInterface) {
        return new CollectionsAdapter(result, clickInterface);
    }

    public UserAdapter getUserAdapter(UserSearchResult result) {
        return new UserAdapter(result);
    }


    public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.PhotoViewHolder> {

        private List<PhotoSearchResult.SearchPhoto> searchPhotos;
        private ClickInterface clickInterface;

        public WallpaperAdapter(PhotoSearchResult photoSearchResult, ClickInterface clickInterface) {

            this.searchPhotos = photoSearchResult.getSearchPhotos();
            this.clickInterface = clickInterface;

        }

        @NonNull
        @Override
        public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            final View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.featured_image_view_holder, viewGroup, false);

            (view.findViewById(R.id.imageView)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickInterface.onClick(view);
                }
            });

            return new PhotoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PhotoViewHolder photoViewHolder, int i) {

            Picasso.get()
                    .load(searchPhotos.get(i).getUrls().getRegular())
                    .placeholder(R.drawable.placeholder)
                    .fit()
                    .centerCrop()
                    .into(photoViewHolder.imageView);

        }

        @Override
        public int getItemCount() {

            return searchPhotos.size();
        }

        class PhotoViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;

            public PhotoViewHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.imageView);
            }
        }

        public void addPhotos(List<PhotoSearchResult.SearchPhoto> photos) {

            int lastPosition = searchPhotos.size();
            searchPhotos.addAll(lastPosition, photos);
            notifyItemRangeInserted(lastPosition, photos.size());
        }

    }


    public class CollectionsAdapter extends com.example.udhay.randomwallpaper.adapters.CollectionsAdapter {

        CollectionSearchResult searchResult;

        CollectionsAdapter(CollectionSearchResult result, ClickInterface clickInterface) {
            super(result.getCollections(), clickInterface);
            searchResult = result;
        }

        @Override
        public void onBindViewHolder(@NonNull CollectionViewHolder collectionViewHolder, int i) {
            super.onBindViewHolder(collectionViewHolder, i);


            Picasso.get()
                    .load(super.collections.get(i).getCoverPhoto().getUrls().getRegular())
                    .placeholder(R.drawable.placeholder)
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


    public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

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
                    .placeholder(R.drawable.placeholder)
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
