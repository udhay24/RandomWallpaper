package com.example.udhay.randomwallpaper.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends BaseAdapter {

    private Context context;

    public ImageAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            ImageView imageView = new ImageView(parent.getContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT));
            convertView = imageView;
        }


        Picasso.get().load("https://picsum.photos/200/300/?random").purgeable().into((ImageView)convertView);

        return convertView;
    }
}
