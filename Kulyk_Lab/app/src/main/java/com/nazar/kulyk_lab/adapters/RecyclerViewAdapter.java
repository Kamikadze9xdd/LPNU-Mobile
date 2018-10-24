package com.nazar.kulyk_lab.adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nazar.kulyk_lab.R;
import com.nazar.kulyk_lab.activities.MainActivity;
import com.nazar.kulyk_lab.adapters.holders.ListItemViewHolder;
import com.nazar.kulyk_lab.fragments.details.DetailsItemFragment;
import com.nazar.kulyk_lab.models.artObjects.ArtObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ListItemViewHolder> {
    private ArrayList<ArtObject> artObjects = new ArrayList<>();

    public void addAll(List<ArtObject> artObjects) {
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
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,
                viewGroup, false);

        final ListItemViewHolder recyclerViewHolder =  new ListItemViewHolder(view);

        recyclerViewHolder.cardView.setOnClickListener(v -> {

            MainActivity mainActivity = (MainActivity) view.getContext();

            Log.d("RecyclerView",String.valueOf(recyclerViewHolder.getAdapterPosition()));

            FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            DetailsItemFragment detailsItemFragment = new DetailsItemFragment();

            Bundle bundle = new Bundle();
            ArtObject artObject = artObjects.get(recyclerViewHolder.getAdapterPosition());
            bundle.putSerializable("current_item", artObject);
            detailsItemFragment.setArguments(bundle);

            ft.replace(R.id.container, detailsItemFragment).addToBackStack(null).commit();
        });

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder viewHolder, int position) {
        viewHolder.short_title.setText(artObjects.get(position).getTitle());
        viewHolder.author.setText(artObjects.get(position).getPrincipalOrFirstMaker());
        Picasso.get().load(artObjects.get(position).getHeaderImage().getUrl()).into(viewHolder.web_image);
    }

    @Override
    public int getItemCount() {
        return artObjects.size();
    }
}
