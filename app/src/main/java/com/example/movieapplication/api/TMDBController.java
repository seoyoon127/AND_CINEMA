package com.example.movieapplication.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TMDBController {
    @GET("now_playing?language=ko-KR&page=1")
    Call<NowPlayingResponse> getNowPlaying();
}