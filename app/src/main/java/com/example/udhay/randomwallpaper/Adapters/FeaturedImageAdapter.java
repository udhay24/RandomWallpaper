package com.example.udhay.randomwallpaper.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.udhay.randomwallpaper.DB.TinyDB;
import com.example.udhay.randomwallpaper.MainActivity;
import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.WallpaperActivity;
import com.example.udhay.randomwallpaper.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FeaturedImageAdapter extends RecyclerView.Adapter<FeaturedImageAdapter.PhotoViewHolder> {

    private Context context;

    private List<Photo> photoList;

    public FeaturedImageAdapter(List<Photo> list) {

        this.photoList = list;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.featured_image_view_holder, viewGroup, false);

        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PhotoViewHolder photoViewHolder, int i) {

        final Photo photo = photoList.get(i);

        Picasso.get()
                .load(photo.getUrls().getRegular())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .fit()
                .centerCrop()
                .into(photoViewHolder.getView());

        photoViewHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WallpaperActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context,
                                photoViewHolder.imageView,
                                ViewCompat.getTransitionName(photoViewHolder.imageView));
                TinyDB db = new TinyDB(context);
                db.putString("ImageID",photo.getUrls().getRegular());
                db.putString("Author",photo.getUser().getName());
                context.startActivity(intent,options.toBundle());
            }
        });

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
