package com.example.testdproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Picture extends RealmObject {

    @SerializedName(value="large")
    @Expose
    public String large;
    @SerializedName(value="medium")
    @Expose
    public String medium;
    @SerializedName(value="thumbnail")
    @Expose
    public String thumbnail;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
