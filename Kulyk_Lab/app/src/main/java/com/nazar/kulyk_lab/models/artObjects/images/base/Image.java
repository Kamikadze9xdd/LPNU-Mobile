package com.nazar.kulyk_lab.models.artObjects.images.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("guid")
    @Expose
    private String guid;

    @SerializedName("offsetPercentageX")
    @Expose
    private String offsetPercentageX;

    @SerializedName("offsetPercentageY")
    @Expose
    private String offsetPercentageY;

    @SerializedName("width")
    @Expose
    private String width;

    @SerializedName("height")
    @Expose
    private String height;

    @SerializedName("url")
    @Expose
    private String url;

    @Override
    public String toString() {
        return "\n\tGuid: " + getGuid() +
                "\n\tOffsetPercentageX: " + getOffsetPercentageX() +
                "\n\tOffsetPercentageY: " + getOffsetPercentageY() +
                "\n\tWidth: " + getWidth() + "\n\tHeight: " + getHeight() +
                "\n\tUrl: " + getUrl();
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOffsetPercentageX() {
        return offsetPercentageX;
    }

    public void setOffsetPercentageX(String offsetPercentageX) {
        this.offsetPercentageX = offsetPercentageX;
    }

    public String getOffsetPercentageY() {
        return offsetPercentageY;
    }

    public void setOffsetPercentageY(String offsetPercentageY) {
        this.offsetPercentageY = offsetPercentageY;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
