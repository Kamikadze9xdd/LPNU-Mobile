package com.nazar.kulyk_lab.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nazar.kulyk_lab.R;
import com.nazar.kulyk_lab.activities.MainActivity;
import com.nazar.kulyk_lab.adapters.holders.FavouritesItemViewHolder;
import com.nazar.kulyk_lab.models.artObjects.ArtObject;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavouritesItemsRecyclerViewAdapter extends RecyclerView.Adapter<FavouritesItemViewHolder> {
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
    public FavouritesItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favourite_item_card,
                viewGroup, false);

        final FavouritesItemViewHolder recyclerViewHolder =  new FavouritesItemViewHolder(view);

        recyclerViewHolder.button.setOnClickListener(v -> {
            removeFromFav(recyclerViewHolder.getAdapterPosition(), view);
            Log.d("FavList","Delete " + String.valueOf(recyclerViewHolder.getAdapterPosition()));
        });
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesItemViewHolder viewHolder, int position) {
        viewHolder.short_title.setText(artObjects.get(position).getTitle());
        viewHolder.author.setText(artObjects.get(position).getPrincipalOrFirstMaker());
        Picasso.get().load(artObjects.get(position).getHeaderImage().getUrl()).into(viewHolder.web_image);
    }

    @Override
    public int getItemCount() {
        return artObjects.size();
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void removeFromFav(int position, View view){
        ArrayList<ArtObject> alreadySaved = new ArrayList<>();
        final ArtObject remove_obj = artObjects.get(position);
        Gson gson = new Gson();
        MainActivity mainActivity = (MainActivity) view.getContext();
        SharedPreferences sharedPref = mainActivity.getApplicationContext().getSharedPreferences(
                "fav_list", Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString("fav_list", "");
        Log.i("favs", jsonPreferences);

        if(!jsonPreferences.equals("")){
            Type type = new TypeToken<List<ArtObject>>() {}.getType();
            alreadySaved = gson.fromJson(jsonPreferences, type);
            Log.i("users", artObjects.toString());
            alreadySaved.removeIf(t -> t.getId().equals(remove_obj.getId()));
            Toast.makeText(mainActivity,"Remove from favourites", Toast.LENGTH_SHORT).show();
            String json = gson.toJson(alreadySaved);
            SharedPreferences sharedPreferences = mainActivity.getApplicationContext().getSharedPreferences(
                    "fav_list",
                    Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("fav_list",json);
            editor.apply();
        }
    }
}
