package com.postservice.model;

public class Image {

    private String imageUrl;
    private String imageLocation;

    public Image(String imageUrl, String imageLocation) {
        this.imageUrl = imageUrl;
        this.imageLocation = imageLocation;
    }

    public Image() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }
}
