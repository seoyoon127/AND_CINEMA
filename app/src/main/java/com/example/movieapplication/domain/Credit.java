package com.example.movieapplication.domain;

import com.google.gson.annotations.SerializedName;

public class Credit {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("known_for_department")
    private String knownForDepartment;

    @SerializedName("character")
    private String character;

    @SerializedName("profile_path")
    private String profilePath;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public String getCharacter() {
        return character;
    }

    public String getProfilePath() {
        return profilePath;
    }
}