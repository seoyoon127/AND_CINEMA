package com.example.movieapplication.api;

import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.BookingActivity;
import com.example.movieapplication.R;
import com.example.movieapplication.components.BookUI;
import com.example.movieapplication.components.LikesUI;
import com.example.movieapplication.components.MovieDetailUI;
import com.example.movieapplication.domain.MovieDetail;
import com.google.android.flexbox.FlexboxLayout;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UseMovieDetailApi {

    private TMDBController api;
    private AppCompatActivity activity;

    public UseMovieDetailApi(AppCompatActivity activity) {
        this.activity = activity;
        api = TMDBApi.getRetrofit().create(TMDBController.class);
    }

    public void loadMovie(int id, LinearLayout buttons) {
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

    public void loadMovieName(int id, MovieNameCallback callback) {
        api.getMovieDetail(id).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResult(response.body().getTitle());
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                t.printStackTrace();
                callback.onResult(null);
            }
        });
    }

    public void loadBackdropPath(int id, BackdropCallback callback){
        api.getMovieDetail(id).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResult(response.body().getBackdropPath());
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                t.printStackTrace();
                callback.onResult(null);
            }
        });
    }

    public void loadMovieInfo(int id, LinearLayout movieContainer) {
        api.getMovieDetail(id).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful() && response.body() != null) {
                    BookUI.setMovie(activity, response.body(), movieContainer);
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void loadMovieTime(int id, FlexboxLayout timeContainer) {
        api.getMovieDetail(id).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful() && response.body() != null) {
                    int runtime = response.body().getRuntime();

                    // 1) 시간 박스 생성
                    BookUI.setTime(activity, timeContainer, runtime, selectedTime -> {
                        // Activity 필드에 선택 시간 저장
                        if (activity instanceof BookingActivity) {
                            ((BookingActivity) activity).timeSelected = selectedTime;
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void loadMoviePoster(Integer id, PosterCallback callback){
        api.getMovieDetail(id).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if(response.isSuccessful() && response.body() != null){
                    String posterPath = response.body().getPosterPath();
                    if (posterPath != null && !posterPath.isEmpty()) {
                        String url = "https://image.tmdb.org/t/p/w500" + posterPath;
                        callback.onResult(url); // URL만 전달
                    } else {
                        callback.onResult(null);
                    }
                } else {
                    callback.onResult(null);
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                t.printStackTrace();
                callback.onResult(null);
            }
        });
    }


    // 콜백 인터페이스 정의
    public interface PosterCallback {
        void onResult(String posterUrl);
    }

}
