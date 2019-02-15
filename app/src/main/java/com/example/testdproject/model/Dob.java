package com.example.testdproject.model;

import com.google.gson.annotations.*;


import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by mattiaco on 07/02/2019.
 */

public class Dob extends RealmObject {
    @SerializedName(value="date")
    @Expose
    public Date date;
    @SerializedName(value="age")
    @Expose
    public Integer age;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
