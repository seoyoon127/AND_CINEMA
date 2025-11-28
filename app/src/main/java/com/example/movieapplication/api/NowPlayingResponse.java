package com.example.movieapplication.api;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.List;

public class NowPlayingResponse {

    @SerializedName("results")
    private List<Movie> results;

    public List<Movie> getResults() { return results; }
}
