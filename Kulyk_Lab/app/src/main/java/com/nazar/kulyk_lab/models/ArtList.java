package com.nazar.kulyk_lab.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nazar.kulyk_lab.models.artObjects.ArtObjects;

import java.util.ArrayList;

public class ArtList {

    @SerializedName("count")
    @Expose
    private String count;

    @SerializedName("artObjects")
    @Expose
    private ArrayList<ArtObjects> artObjects;

    @Override
    public String toString() {
        return "ArtList{" +
                "count='" + count + '\'' +
                ", artObjects=" + artObjects +
                '}';
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<ArtObjects> getArtObjects() {
        return artObjects;
    }

    public void setArtObjects(ArrayList<ArtObjects> artObjects) {
        this.artObjects = artObjects;
    }
}
