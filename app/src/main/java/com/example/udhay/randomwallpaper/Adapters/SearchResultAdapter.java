package com.example.udhay.randomwallpaper.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.model.PhotoSearchResult;
import com.squareup.picasso.Picasso;

import timber.log.Timber;

public class SearchResultAdapter {

    public WallpaperAdapter getWallpaperAdapter(PhotoSearchResult result) {
        return new WallpaperAdapter(result);
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
}
