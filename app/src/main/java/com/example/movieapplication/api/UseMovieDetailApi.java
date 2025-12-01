package com.example.movieapplication.api;

import android.app.Activity;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.movieapplication.R;
import com.example.movieapplication.components.MovieDetailUI;
import com.example.movieapplication.domain.MovieDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UseMovieDetailApi {

    private TMDBController api;
    private Activity activity;

    public UseMovieDetailApi(Activity activity) {
        this.activity = activity;
        api = TMDBApi.getRetrofit().create(TMDBController.class);
    }

    public void loadMovies(int id, LinearLayout buttons) {
        api.getMovieDetail(id).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MovieDetail movieDetail = response.body();
                    LinearLayout movieContainer = activity.findViewById(R.id.movieContainer);
                    MovieDetailUI.setMovie(activity, movieDetail, movieContainer, buttons);
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
