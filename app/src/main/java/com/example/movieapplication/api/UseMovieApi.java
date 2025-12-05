package com.example.movieapplication.api;

import android.app.Activity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.movieapplication.R;
import com.example.movieapplication.components.NowPlayingMovieUI;
import com.example.movieapplication.domain.Movie;
import com.example.movieapplication.domain.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UseMovieApi {

    private TMDBController api;
    private Activity activity;

    public UseMovieApi(Activity activity) {
        this.activity = activity;
        api = TMDBApi.getRetrofit().create(TMDBController.class);
    }

    public void loadMovies(String category) {

        api.getMoviesByCategory(category).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();

                    // UI 갱신은 반드시 UI 스레드에서
                    activity.runOnUiThread(() -> {
                        LinearLayout container = activity.findViewById(R.id.movieContainer);
                        container.removeAllViews(); // 이전 영화 목록 제거
                        for (Movie movie : movies) {
                            NowPlayingMovieUI.setMovie(activity, movie, container);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                android.util.Log.e("MainActivity", "API call failed", t);
            }
        });
    }
}