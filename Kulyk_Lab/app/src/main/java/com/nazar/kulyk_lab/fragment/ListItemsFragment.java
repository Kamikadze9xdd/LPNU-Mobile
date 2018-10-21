package com.nazar.kulyk_lab.fragment;

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

import com.nazar.kulyk_lab.R;
import com.nazar.kulyk_lab.adapters.RecyclerViewAdapter;
import com.nazar.kulyk_lab.interfaces.RijksmuseumApi;
import com.nazar.kulyk_lab.models.ArtList;
import com.nazar.kulyk_lab.models.artObjects.ArtObjects;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.Objects.*;

public class ListItemsFragment extends Fragment {
//    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    TextView noData;
    private RecyclerViewAdapter adapter = new RecyclerViewAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.item_list, container, false);
//        swipeRefreshLayout = view.findViewById(R.id.swipeContainer);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        noData = view.findViewById(R.id.no_data);
        loadData();
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refresh();
//                noData.setVisibility(View.INVISIBLE);
//            }
//        });
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        return view;
    }

    private void loadData() {
        String BASE_URL = "https://www.rijksmuseum.nl/api/eu/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RijksmuseumApi rijksmuseumApi = retrofit.create(RijksmuseumApi.class);
        Call<ArtList> call = rijksmuseumApi.getData();

        call.enqueue(new Callback<ArtList>() {
            String TAG = getString(R.string.TAG);
            @Override
            public void onResponse(@NonNull Call<ArtList> call,
                                   @NonNull Response<ArtList> response) {
                Log.d(TAG, response.toString());
                ArrayList<ArtObjects> artObjects = requireNonNull(response.body()).getArtObjects();
                displayData(artObjects);
            }

            @Override
            public void onFailure(@NonNull Call<ArtList> call, @NonNull Throwable t) {
                Log.e(TAG, t.getMessage());
                noData.setVisibility(View.VISIBLE);
            }
        });
    }

    private void displayData(ArrayList<ArtObjects> artObjects) {
        adapter.addAll(artObjects);
    }

//    public void refresh() {
//        adapter.clear();
//        loadData();
//        swipeRefreshLayout.setRefreshing(false);
//    }
}
