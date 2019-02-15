package com.example.testdproject.model;
import com.google.gson.annotations.*;

import io.realm.RealmObject;

public class User extends RealmObject {
    @SerializedName(value="name")
    @Expose
    Name name;
    @SerializedName(value="location")
    @Expose
    public Location location;
    @SerializedName(value="email")
    @Expose
    public String email;
    @SerializedName(value="login")
    @Expose
    public Login login;
    @SerializedName(value="dob")
    @Expose
    public Dob dob;
    @SerializedName(value="timezone")
    @Expose
    public Timezone timezone;
    @SerializedName(value="gender")
    @Expose
    public String gender;
    @SerializedName(value="registered")
    @Expose
    public Registered registered;
    @SerializedName(value="phone")
    @Expose
    public String phone;
    @SerializedName(value="picture")
    @Expose
    public Picture picture;
    @SerializedName(value="cell")
    @Expose
    public String cell;
    @SerializedName(value="id")
    @Expose
    public Id id;
    @SerializedName(value="nat")
    @Expose
    public String nat;


    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Dob getDob() {
        return dob;
    }

    public void setDob(Dob dob) {
        this.dob = dob;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }
}
