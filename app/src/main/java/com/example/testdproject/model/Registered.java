package com.example.testdproject.model;
import com.google.gson.annotations.*;
import java.util.Date;

/**
 * Created by mattiaco on 07/02/2019.
 */

public class Registered {
    @SerializedName(value="date")
    @Expose
    public Date registeredDate;
    @SerializedName(value="age")
    @Expose
    public Integer age;

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
