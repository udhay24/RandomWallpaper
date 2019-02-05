package com.example.udhay.randomwallpaper.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.udhay.randomwallpaper.R;
import com.example.udhay.randomwallpaper.model.User;

import java.util.ArrayList;
import java.util.List;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsViewHolder> {

    List<User.Custom> customList;
    List<User.Aggregated> aggregatedList;
    ArrayList<String> arrayList = new ArrayList<>();

    public TagsAdapter(List<User.Custom> list1, List<User.Aggregated> list2) {

        customList = list1;
        aggregatedList = list2;

        for (User.Custom customs : customList) {
            arrayList.add(customs.getTitle());
        }

        for (User.Aggregated aggregated : aggregatedList) {
            arrayList.add(aggregated.getTitle());
        }

    }

    @NonNull
    @Override
    public TagsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tags_view_holder, viewGroup, false);

        return new TagsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagsViewHolder tagsViewHolder, int i) {

        tagsViewHolder.tagTextView.setText(arrayList.get(i));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class TagsViewHolder extends RecyclerView.ViewHolder {

        TextView tagTextView;

        public TagsViewHolder(View view) {
            super(view);
            tagTextView = view.findViewById(R.id.tag_text_view);
        }
    }
}
