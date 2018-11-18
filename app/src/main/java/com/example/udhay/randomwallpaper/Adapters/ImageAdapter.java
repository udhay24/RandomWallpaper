package com.example.udhay.randomwallpaper.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.udhay.randomwallpaper.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.PhotoViewHolder> {

    private Context context;

    private List<Photo> photoList;

    public ImageAdapter(Context context, List<Photo> list) {

        this.context = context;
        this.photoList = list;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = new ImageView(context);

        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder photoViewHolder, int i) {

        Photo photo = photoList.get(i);

        Picasso.get().load(photo.getUrls().getRegular()).into(photoViewHolder.getView());
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }


    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        private View view;

        public PhotoViewHolder(View view) {
            super(view);
            this.view = view;
        }


        public ImageView getView() {


            return (ImageView) view;
        }

    }


}
