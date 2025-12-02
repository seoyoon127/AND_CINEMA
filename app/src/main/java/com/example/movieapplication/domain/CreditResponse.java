package com.example.movieapplication.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditResponse {

    @SerializedName("cast")
    private List<Credit> results;

    public List<Credit> getResults() { return results; }
}
