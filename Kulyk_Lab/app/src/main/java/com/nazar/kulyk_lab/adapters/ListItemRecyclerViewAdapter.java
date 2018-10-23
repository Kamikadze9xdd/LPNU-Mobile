package com.nazar.kulyk_lab.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
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
import com.nazar.kulyk_lab.adapters.holders.ListItemViewHolder;
import com.nazar.kulyk_lab.fragment.DetailsItemFragment;
import com.nazar.kulyk_lab.models.artObjects.ArtObject;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListItemRecyclerViewAdapter extends RecyclerView.Adapter<ListItemViewHolder> {
    private ArrayList<ArtObject> artObjects = new ArrayList<>();
    private Dialog dialog;

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
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card,
                viewGroup, false);

        final ListItemViewHolder recyclerViewHolder =  new ListItemViewHolder(view);

        dialog = new Dialog(viewGroup.getContext());

        recyclerViewHolder.add_to_fav_button.setOnClickListener(v -> {
            saveToFav(recyclerViewHolder.getAdapterPosition(), view);
            Log.d("ListItems","Add " + String.valueOf(recyclerViewHolder.getAdapterPosition()));
        });

        recyclerViewHolder.more_info_button.setOnClickListener(v -> {
            MainActivity mainActivity = (MainActivity) view.getContext();

            FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();

            DetailsItemFragment detailsItemFragment = new DetailsItemFragment();

            fragmentManager.beginTransaction().replace(R.id.container, detailsItemFragment)
                    .addToBackStack(null).commit();
        });
//        recyclerViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                dialog.setContentView(R.layout.item_details);
////                ImageView imageView = dialog.findViewById(R.id.image_detail);
////                Picasso.get().load(artObjects.get(recyclerViewHolder.getAdapterPosition())
////                        .getWebImage()
////                        .getUrl())
////                        .into(imageView);
////                dialog.show();
//
//                Log.d("RecyclerView",String.valueOf(recyclerViewHolder.getAdapterPosition()));
//
//                MainActivity mainActivity = (MainActivity) view.getContext();
//
//                DetailsItemFragment detailsItemFragment = new DetailsItemFragment();
//                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.viewpager_id,
//                        detailsItemFragment).addToBackStack(null).commit();
//            }
//        });

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

    private void saveToFav(int position, View view){
        ArrayList<ArtObject> already_saved = new ArrayList<ArtObject>();
        ArtObject newArtObject = artObjects.get(position);
        MainActivity mainActivity = (MainActivity) view.getContext();
        Gson gson = new Gson();
        SharedPreferences sharedPref = mainActivity.getApplicationContext().getSharedPreferences(
                "fav_list", Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString("fav_list", "");

        if(!jsonPreferences.equals("")){
            boolean already_saved_item = false;
            Type type = new TypeToken<List<ArtObject>>() {}.getType();
            already_saved = gson.fromJson(jsonPreferences, type);
            for(int i=0;i<already_saved.size();i++){
                if(already_saved.get(i).getId().equals(newArtObject.getId())){
                    already_saved_item = true;
                    Log.d("ListItems","Already saved " + already_saved.get(i).getTitle());
                    Toast.makeText(mainActivity, "Already saved in favourites",Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            if(!already_saved_item){
                already_saved.add(newArtObject);
                Toast.makeText(mainActivity, "Add to favourites", Toast.LENGTH_SHORT).show();
            }
            Log.i("favs", already_saved.toString());
            String json = gson.toJson(already_saved);
            SharedPreferences sharedPreferences = mainActivity.getApplicationContext().getSharedPreferences(
                    "fav_list",
                    Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("fav_list",json);
            editor.apply();
        } else{
            already_saved.add(newArtObject);
            Toast.makeText(mainActivity, "Add to favourites", Toast.LENGTH_SHORT).show();
            String json = gson.toJson(already_saved);
            SharedPreferences sharedPreferences = mainActivity.getApplicationContext().getSharedPreferences(
                    "fav_list",
                    Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("fav_list",json);
            editor.apply();
        }
    }
}
