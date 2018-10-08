package com.nazar.kulyk_lab.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nazar.kulyk_lab.R;
import com.nazar.kulyk_lab.adapters.holders.RecyclerViewHolder;
import com.nazar.kulyk_lab.models.artObjects.ArtObjects;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<ArtObjects> artObjects = new ArrayList<>();

    public void addAll(List<ArtObjects> artObjects) {
        int pos = getItemCount();

        this.artObjects.addAll(artObjects);
        notifyItemRangeInserted(pos, this.artObjects.size());
    }

    public void clear() {
        artObjects.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder viewHolder, int i) {
        viewHolder.short_title.setText(artObjects.get(i).getTitle());
        viewHolder.author.setText(artObjects.get(i).getPrincipalOrFirstMaker());
        Picasso.get().load(artObjects.get(i).getWebImage().getUrl()).into(viewHolder.web_image);
    }

    @Override
    public int getItemCount() {
        return artObjects.size();
    }
}
