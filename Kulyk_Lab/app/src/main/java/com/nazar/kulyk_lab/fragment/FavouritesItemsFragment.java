package com.nazar.kulyk_lab.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nazar.kulyk_lab.R;
import com.nazar.kulyk_lab.activities.MainActivity;
import com.nazar.kulyk_lab.adapters.FavouritesItemsRecyclerViewAdapter;
import com.nazar.kulyk_lab.models.artObjects.ArtObject;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesItemsFragment extends Fragment{
    @BindView(R.id.recyclerView_fav)
    RecyclerView recyclerView;
    @BindView(R.id.no_data)
    TextView noData;
    @BindView(R.id.swipeRefreshLayout_fav)
    SwipeRefreshLayout swipeRefreshLayout;

    private FavouritesItemsRecyclerViewAdapter adapter = new FavouritesItemsRecyclerViewAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.item_list_fav, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        loadData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                noData.setVisibility(View.VISIBLE);
                refresh();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        return view;
    }

    private void loadData(){
        Gson gson = new Gson();
        List<ArtObject> artObjects;

        MainActivity mainActivity = (MainActivity) recyclerView.getContext();
        SharedPreferences sharedPref = mainActivity.getApplicationContext().getSharedPreferences(
                "fav_list", Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString("fav_list", "");
        Log.i("favs", jsonPreferences);

        if(!jsonPreferences.equals("")){
            Type type = new TypeToken<List<ArtObject>>() {}.getType();
            artObjects = gson.fromJson(jsonPreferences, type);
            Log.i("users", artObjects.toString());

            adapter.addAll(artObjects);
        }
    }

    public void refresh() {
        adapter.clear();
        loadData();
        swipeRefreshLayout.setRefreshing(false);
    }
}
