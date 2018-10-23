package com.nazar.kulyk_lab.models;

import com.google.gson.annotations.SerializedName;
import com.nazar.kulyk_lab.models.artObjects.ArtObject;

import java.util.ArrayList;

public class ArtList {

    @SerializedName("count")
    private String count;

    @SerializedName("artObjects")
    private ArrayList<ArtObject> artObjects;

    @Override
    public String toString() {
        return "ArtList{" +
                "count='" + count + '\'' +
                ", artObjects=" + artObjects +
                '}';
    }

    public ArrayList<ArtObject> getArtObjects() {
        return artObjects;
    }
}
