package com.nazar.kulyk_lab.models.artObjects.links;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    @Expose
    private String self;

    @SerializedName("web")
    @Expose
    private String web;

    @Override
    public String toString() {
        return "Links{" +
                "self='" + self + '\'' +
                ", web='" + web + '\'' +
                '}';
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
