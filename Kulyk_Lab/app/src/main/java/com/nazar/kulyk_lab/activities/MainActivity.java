package com.nazar.kulyk_lab.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nazar.kulyk_lab.R;
import com.nazar.kulyk_lab.interfaces.RijksmuseumApi;
import com.nazar.kulyk_lab.models.ArtList;
import com.nazar.kulyk_lab.models.artObjects.ArtObjects;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button get_data_button;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_data_button = findViewById(R.id.get_data_button);
        onClickGetDataButton();
    }

    private void onClickGetDataButton(){
        get_data_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData(){
        String BASE_URL = "https://www.rijksmuseum.nl/api/eu/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RijksmuseumApi rijksmuseumApi = retrofit.create(RijksmuseumApi.class);
        Call<ArtList> call = rijksmuseumApi.getData();

        call.enqueue(new Callback<ArtList>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NonNull Call<ArtList> call,
                                   @NonNull Response<ArtList> response) {
                Log.d(TAG, "onResponse: ServerResponse: " + response.toString());

                ArrayList<ArtObjects> artObjects = Objects.requireNonNull(response.body())
                        .getArtObjects();
                displayData(artObjects);

            }

            @Override
            public void onFailure(@NonNull Call<ArtList> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void displayData(ArrayList<ArtObjects> artObjects){
        for (int i = 0; i < artObjects.size(); i++) {
            Log.d(TAG, artObjects.get(i).toString() +
                    "\n----------------------------------------");
        }
    }
}
