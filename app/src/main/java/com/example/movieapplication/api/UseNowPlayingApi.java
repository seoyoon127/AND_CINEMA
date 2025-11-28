package com.example.movieapplication.api;

import android.app.Activity;
import android.widget.LinearLayout;

import com.example.movieapplication.R;
import com.example.movieapplication.components.NowPlayingMovieUI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UseNowPlayingApi {

    private TMDBController api;
    private Activity activity;

    public UseNowPlayingApi(Activity activity) {
        this.activity = activity;
        api = TMDBApi.getRetrofit().create(TMDBController.class);
    }

    public void loadMovies() {
        api.getNowPlaying().enqueue(new Callback<NowPlayingResponse>() {
            @Override
            public void onResponse(Call<NowPlayingResponse> call, Response<NowPlayingResponse> response) {
                LinearLayout movieContainer = activity.findViewById(R.id.movieContainer);
                if (response.isSuccessful() && response.body() != null) {
                    NowPlayingResponse data = response.body();

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (Movie movie : data.getResults()) {
                                NowPlayingMovieUI.setMovie(activity, movie, movieContainer);
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<NowPlayingResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

