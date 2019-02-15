package com.example.testdproject.model;
import com.google.gson.annotations.*;

import io.realm.RealmObject;

/**
 * Created by mattiaco on 07/02/2019.
 */

public class Id extends RealmObject {
    @SerializedName(value="name")
    @Expose
    public String idName;
    @SerializedName(value="value")
    @Expose
    public String value;

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {

        this.idName = idName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
