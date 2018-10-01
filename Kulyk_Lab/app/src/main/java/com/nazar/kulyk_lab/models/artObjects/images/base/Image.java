package com.nazar.kulyk_lab.models.artObjects.images.base;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("guid")
    private String guid;

    @SerializedName("offsetPercentageX")
    private String offsetPercentageX;

    @SerializedName("offsetPercentageY")
    private String offsetPercentageY;

    @SerializedName("width")
    private String width;

    @SerializedName("height")
    private String height;

    @SerializedName("url")
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

    public String getOffsetPercentageX() {
        return offsetPercentageX;
    }

    public String getOffsetPercentageY() {
        return offsetPercentageY;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }
}
