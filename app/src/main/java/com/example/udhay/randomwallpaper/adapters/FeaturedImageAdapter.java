package com.example.udhay.randomwallpaper.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.interfaces.ClickInterface;
import com.example.udhay.randomwallpaper.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FeaturedImageAdapter extends RecyclerView.Adapter<FeaturedImageAdapter.PhotoViewHolder> {

    private Context context;

    private static List<Photo> photoList;

    private ClickInterface clickInterface;

    public FeaturedImageAdapter(List<Photo> list, ClickInterface clickInterface) {

        photoList = list;

        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        this.context = viewGroup.getContext();
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.featured_image_view_holder, viewGroup, false);

        PhotoViewHolder viewHolder = new PhotoViewHolder(view);

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickInterface.onClick(view);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder photoViewHolder, int i) {

        Photo photo = photoList.get(i);

        Picasso.get()
                .load(photo.getUrls().getRegular())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .fit()
                .centerCrop()
                .into(photoViewHolder.getView());

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

    public List<Photo> getList() {
        return photoList;
    }
}
