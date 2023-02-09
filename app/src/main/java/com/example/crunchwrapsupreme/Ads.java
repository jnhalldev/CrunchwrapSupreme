package com.example.crunchwrapsupreme;

import com.google.firebase.database.ServerValue;

public class Ads {

    private String adId;
    private String name;
    private String description;
    private String image;
    private String url;
    private Long timestamp;

    public Ads(String adId, String name, String description, String image, String url, Long timestamp) {
        this.adId = adId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.url = url;
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ads() {
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
