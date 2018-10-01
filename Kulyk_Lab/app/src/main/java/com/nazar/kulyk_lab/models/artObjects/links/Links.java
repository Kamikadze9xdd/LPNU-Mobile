package com.nazar.kulyk_lab.models.artObjects.links;

import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    private String self;

    @SerializedName("web")
    private String web;

    @Override
    public String toString() {
        return "\n\tSelf: " + getSelf() +
                "\n\tWeb: " + getWeb();
    }

    public String getSelf() {
        return self;
    }

    public String getWeb() {
        return web;
    }

}
