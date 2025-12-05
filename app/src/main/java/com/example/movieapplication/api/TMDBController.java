package com.example.movieapplication.api;

import com.example.movieapplication.domain.CreditResponse;
import com.example.movieapplication.domain.MovieDetail;
import com.example.movieapplication.domain.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TMDBController {
    @GET("{category}?language=ko-KR")
    Call<MoviesResponse> getMoviesByCategory(@Path("category") String category);

    @GET("{id}?language=ko-KR")
    Call<MovieDetail> getMovieDetail(@Path("id") int id);

    @GET("{id}/credits?language=ko-KR")
    Call<CreditResponse> getCredit(@Path("id") int id);

}