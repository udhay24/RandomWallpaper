package com.example.udhay.randomwallpaper.adapters;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.interfaces.ClickInterface;
import com.example.udhay.randomwallpaper.model.Collection;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CollectionsAdapter extends RecyclerView.Adapter<CollectionsAdapter.CollectionViewHolder> {


    List<Collection> collections;

    ClickInterface clickInterface;

    public CollectionsAdapter(List<Collection> collectionList, ClickInterface clickInterface) {
        collections = collectionList;
        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.collection_view_holder, viewGroup, false);

        CollectionViewHolder viewHolder = new CollectionViewHolder(view);

        viewHolder.collectionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickInterface.onClick(view);
            }
        });
        return new CollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder collectionViewHolder, int i) {

        collectionViewHolder.collectionTitle.setText(collections.get(i).getTitle());

        Picasso.get()
                .load(collections.get(i).getCoverPhoto().getUrls().getSmall())
                .into(collectionViewHolder.collectionImage);
    }

    @Override
    public int getItemCount() {
        return collections.size();
    }

    public void addCollection(List<Collection> list) {

        int lastPosition = this.getItemCount();
        collections.addAll(list);
        this.notifyItemRangeInserted(lastPosition, list.size());

    }

    public class CollectionViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView collectionImage;
        TextView collectionTitle;
        CardView collectionCardView;
        FrameLayout collectionFrameLayout;

        public CollectionViewHolder(View view) {
            super(view);
            collectionImage = view.findViewById(R.id.collection_image);
            collectionTitle = view.findViewById(R.id.collection_title);
            collectionCardView = view.findViewById(R.id.collection_card_view);
            collectionFrameLayout = view.findViewById(R.id.collection_frame_layout);
        }
    }

    public List<Collection> getCollections() {
        return collections;
    }
}
