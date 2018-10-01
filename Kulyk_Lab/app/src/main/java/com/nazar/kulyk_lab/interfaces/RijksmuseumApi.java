package com.nazar.kulyk_lab.interfaces;

import com.nazar.kulyk_lab.models.ArtList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RijksmuseumApi {
    @Headers("Content-Type: application/json")
    @GET("collection?key=3KgtVtTF&format=json")
    Call<ArtList> getData();
}
