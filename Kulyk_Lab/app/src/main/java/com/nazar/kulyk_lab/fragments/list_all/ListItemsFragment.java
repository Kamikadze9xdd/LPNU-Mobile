package com.nazar.kulyk_lab.fragments.list_all;

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
import com.nazar.kulyk_lab.models.ArtList;
import com.nazar.kulyk_lab.models.artObjects.ArtObject;
import com.nazar.kulyk_lab.netconnection.RetrofitClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Objects.requireNonNull;

public class ListItemsFragment extends Fragment {
    @BindView(R.id.recyclerView_all)
    protected RecyclerView recyclerView;
    @BindView(R.id.no_data)
    protected TextView noData;
    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerViewAdapter adapter = new RecyclerViewAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.list_all, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        loadData();
        swipeRefreshLayout.setOnRefreshListener(() -> {
            noData.setVisibility(View.VISIBLE);
            refresh();
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        return view;
    }

    private void loadData() {
        RetrofitClient retrofitClient = new RetrofitClient();
        Call call = retrofitClient.init();
        call.enqueue(new Callback<ArtList>() {
            String TAG = getString(R.string.TAG);
            @Override
            public void onResponse(@NonNull Call<ArtList> call,
                                   @NonNull Response<ArtList> response) {
                noData.setVisibility(View.INVISIBLE);
                Log.d(TAG, response.toString());
                ArrayList<ArtObject> artObjects = requireNonNull(response.body()).getArtObjects();
                displayData(artObjects);
            }

            @Override
            public void onFailure(@NonNull Call<ArtList> call, @NonNull Throwable t) {
                Log.e(TAG, t.getMessage());
                noData.setVisibility(View.VISIBLE);
            }
        });
    }

    private void displayData(ArrayList<ArtObject> artObjects) {
        adapter.addAll(artObjects);
    }

    public void refresh() {
        adapter.clear();
        loadData();
        swipeRefreshLayout.setRefreshing(false);
    }
}
