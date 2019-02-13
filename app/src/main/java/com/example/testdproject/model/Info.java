package com.example.testdproject.model;
import com.google.gson.annotations.*;
/**
 * Created by mattiaco on 07/02/2019.
 */

public class Info {
    @SerializedName(value="seed")
    @Expose
    public String seed;
    @SerializedName(value="results")
    @Expose
    public int result;
    @SerializedName(value="page")
    @Expose
    public int page;
    @SerializedName(value="version")
    @Expose
    public String version;

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
