package com.example.testdproject.model;

import com.google.gson.annotations.*;

import io.realm.RealmObject;


/**
 * Created by mattiaco on 07/02/2019.
 */

public class Name extends RealmObject {
    @SerializedName(value="title")
    @Expose
    public String title;
    @SerializedName(value="first")
    @Expose
    public String first;
    @SerializedName(value="last")
    @Expose
    public String last;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
