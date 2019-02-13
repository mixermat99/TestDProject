package com.example.testdproject.model;

import com.google.gson.annotations.*;


/**
 * Created by mattiaco on 07/02/2019.
 */

public class Timezone {
    @SerializedName(value="offset")
    @Expose
    public String offSet;
    @SerializedName(value="description")
    @Expose
    public String description;

    public String getOffSet() {
        return offSet;
    }

    public void setOffSet(String offSet) {
        this.offSet = offSet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
