package com.example.testdproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by mattiaco on 11/02/2019.
 */

public class Data {

    @SerializedName("results")
    @Expose
    public ArrayList<User> results;
    @SerializedName("info")
    @Expose
    public Info info;

    public ArrayList<User> getResults() {
        return results;
    }

    public void setResults(ArrayList<User> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
