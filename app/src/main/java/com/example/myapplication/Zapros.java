package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Zapros {

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    public Zapros(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
