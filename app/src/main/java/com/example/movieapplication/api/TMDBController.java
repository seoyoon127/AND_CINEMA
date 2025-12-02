package com.example.movieapplication.api;

import com.example.movieapplication.domain.Credit;
import com.example.movieapplication.domain.CreditResponse;
import com.example.movieapplication.domain.MovieDetail;
import com.example.movieapplication.domain.NowPlayingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBController {
    @GET("now_playing?language=ko-KR&page=1")
    Call<NowPlayingResponse> getNowPlaying();

    @GET("{id}?language=ko-KR")
    Call<MovieDetail> getMovieDetail(@Path("id") int id);

    @GET("{id}/credits?language=ko-KR")
    Call<CreditResponse> getCredit(@Path("id") int id);

}