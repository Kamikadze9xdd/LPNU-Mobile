package com.nazar.kulyk_lab.netconnection;

import com.nazar.kulyk_lab.interfaces.RijksmuseumApi;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public Call init(){
        String BASE_URL = "https://www.rijksmuseum.nl/api/eu/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RijksmuseumApi rijksmuseumApi = retrofit.create(RijksmuseumApi.class);

        return rijksmuseumApi.getData();
    }
}
