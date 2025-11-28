package com.example.movieapplication.domain;

import com.example.movieapplication.domain.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlayingResponse {

    @SerializedName("results")
    private List<Movie> results;

    public List<Movie> getResults() { return results; }
}
