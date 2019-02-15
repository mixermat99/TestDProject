package com.example.testdproject.model;
import com.google.gson.annotations.*;

import io.realm.RealmObject;

/**
 * Created by mattiaco on 07/02/2019.
 */

public class Login extends RealmObject {
    @SerializedName(value="uuid")
    @Expose
    public String uuid;
    @SerializedName(value="username")
    @Expose
    public String userName;
    @SerializedName(value="password")
    @Expose
    public String Password;
    @SerializedName(value="salt")
    @Expose
    public String salt;
    @SerializedName(value="md5")
    @Expose
    public String md5;
    @SerializedName(value="sha1")
    @Expose
    public String sha1;
    @SerializedName(value="sha256")
    @Expose
    public String sha256;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }


}
