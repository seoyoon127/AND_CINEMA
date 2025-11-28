package com.example.movieapplication.api;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private float voteAverage;

    public String getTitle() { return title; }
    public String getPosterPath() { return posterPath; }
    public String getReleaseDate() { return releaseDate; }
    public float getVoteAverage() { return voteAverage; }
}
