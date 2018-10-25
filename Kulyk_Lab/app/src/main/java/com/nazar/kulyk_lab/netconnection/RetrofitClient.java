package com.nazar.kulyk_lab.netconnection;

import android.app.Application;

import com.nazar.kulyk_lab.interfaces.RijksmuseumApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient extends Application{

    private RijksmuseumApi rijksmuseumApi;

    @Override
    public void onCreate() {
        setRijksmuseumApi();
        super.onCreate();
    }

    public void setRijksmuseumApi(){
        String BASE_URL = "https://www.rijksmuseum.nl/api/eu/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        rijksmuseumApi = retrofit.create(RijksmuseumApi.class);
    }

    public RijksmuseumApi getRijksmuseumApi() {
        return rijksmuseumApi;
    }
}
