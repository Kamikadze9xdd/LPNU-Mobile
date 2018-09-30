package com.nazar.kulyk_lab.models.artObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nazar.kulyk_lab.models.artObjects.images.HeaderImage;
import com.nazar.kulyk_lab.models.artObjects.images.WebImage;
import com.nazar.kulyk_lab.models.artObjects.links.Links;

public class ArtObjects {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("longTitle")
    @Expose
    private String longTitle;

    @SerializedName("links")
    @Expose
    private Links links;

    @SerializedName("webImage")
    @Expose
    private WebImage webImage;

    @SerializedName("headerImage")
    @Expose
    private HeaderImage headerImage;

    @SerializedName("principalOrFirstMaker")
    @Expose
    private String principalOrFirstMaker;

    @Override
    public String toString() {
        return "\nId: " + getId() +
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

    public void setPrincipalOrFirstMaker(String principalOrFirstMaker) {
        this.principalOrFirstMaker = principalOrFirstMaker;
    }

    public String getObjectNumber() {
        return objectNumber;
    }

    public void setObjectNumber(String objectNumber) {
        this.objectNumber = objectNumber;
    }

    @SerializedName("objectNumber")

    @Expose
    private String objectNumber;

    public HeaderImage getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(HeaderImage headerImage) {
        this.headerImage = headerImage;
    }

    public WebImage getWebImage() {
        return webImage;
    }

    public void setWebImage(WebImage webImage) {
        this.webImage = webImage;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public void setLongTitle(String longTitle) {
        this.longTitle = longTitle;
    }
}
