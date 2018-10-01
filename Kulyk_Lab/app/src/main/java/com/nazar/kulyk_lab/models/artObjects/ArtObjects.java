package com.nazar.kulyk_lab.models.artObjects;

import com.google.gson.annotations.SerializedName;
import com.nazar.kulyk_lab.models.artObjects.images.HeaderImage;
import com.nazar.kulyk_lab.models.artObjects.images.WebImage;
import com.nazar.kulyk_lab.models.artObjects.links.Links;

public class ArtObjects {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("longTitle")
    private String longTitle;

    @SerializedName("links")
    private Links links;

    @SerializedName("webImage")
    private WebImage webImage;

    @SerializedName("headerImage")
    private HeaderImage headerImage;

    @SerializedName("principalOrFirstMaker")
    private String principalOrFirstMaker;

    @SerializedName("objectNumber")
    private String objectNumber;

    @Override
    public String toString() {
        return "\nId: " + getId() +
                "\nLinks: " + getLinks().toString() +
                "\nObjectNumber: " + getObjectNumber() +
                "\nTitle: " + getTitle() +
                "\nPrincipalOrFirstMaker: " + getPrincipalOrFirstMaker() +
                "\nLongTitle: " + getLongTitle() +
                "\nWebImage: " + getWebImage().toString() +
                "\nHeaderImage: " + getHeaderImage().toString();
    }

    public String getPrincipalOrFirstMaker() {
        return principalOrFirstMaker;
    }

    public String getObjectNumber() {
        return objectNumber;
    }

    public HeaderImage getHeaderImage() {
        return headerImage;
    }


    public WebImage getWebImage() {
        return webImage;
    }

    public Links getLinks() {
        return links;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLongTitle() {
        return longTitle;
    }
}
