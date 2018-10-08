package com.nazar.kulyk_lab.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nazar.kulyk_lab.R;
import com.nazar.kulyk_lab.adapters.RecyclerViewAdapter;
import com.nazar.kulyk_lab.interfaces.RijksmuseumApi;
import com.nazar.kulyk_lab.models.ArtList;
import com.nazar.kulyk_lab.models.artObjects.ArtObjects;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.no_data)
    TextView no_data;
    private LinearLayoutManager linerLayoutManager = new LinearLayoutManager(this);
    private RecyclerViewAdapter adapter = new RecyclerViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getData();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                no_data.setVisibility(View.INVISIBLE);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(R.color.colorPrimaryDark);
    }

    public void getData() {
        String BASE_URL = "https://www.rijksmuseum.nl/api/eu/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RijksmuseumApi rijksmuseumApi = retrofit.create(RijksmuseumApi.class);
        Call<ArtList> call = rijksmuseumApi.getData();

        call.enqueue(new Callback<ArtList>() {
            @Override
            public void onResponse(@NonNull Call<ArtList> call,
                                   @NonNull Response<ArtList> response) {
                Log.d(TAG, response.toString());
                ArrayList<ArtObjects> artObjects = Objects.requireNonNull(response.body()).getArtObjects();
                displayData(artObjects);
            }

            @Override
            public void onFailure(@NonNull Call<ArtList> call, @NonNull Throwable t) {
                Log.e(TAG, t.getMessage());
                no_data.setVisibility(View.VISIBLE);
            }
        });
    }

    private void displayData(ArrayList<ArtObjects> artObjects) {
        recyclerView.setLayoutManager(linerLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.addAll(artObjects);
    }

    public void refresh() {
        adapter.clear();
        getData();
        swipeContainer.setRefreshing(false);
    }
}
