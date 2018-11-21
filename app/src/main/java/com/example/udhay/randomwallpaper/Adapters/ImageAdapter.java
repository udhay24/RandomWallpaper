package com.example.udhay.randomwallpaper.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.PhotoViewHolder> {

    private Context context;

    private List<Photo> photoList;

    public ImageAdapter( List<Photo> list) {

        this.photoList = list;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_adapter_view_holder , viewGroup , false);

        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder photoViewHolder, int i) {

        Photo photo = photoList.get(i);

        Picasso.get().load(photo.getUrls().getRegular()).placeholder(R.mipmap.loading_round).into(photoViewHolder.getView());
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }


    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        private View view;

        private ImageView imageView;

        public PhotoViewHolder(View view) {
            super(view);
            this.view = view;
            imageView = view.findViewById(R.id.imageView);
        }


        public ImageView getView() {
            return imageView;
        }

    }

    public void addPhotoList(List<Photo> photos){

        int previousCount = this.getItemCount();
        photoList.addAll(photos);
        this.notifyItemRangeInserted(previousCount , photos.size());

    }

    public void swapData(List<Photo> photos){
        photoList = photos;
        this.notifyDataSetChanged();
    }

}
