package com.example.movieapplication.components;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapplication.MovieDetailActivity;
import com.example.movieapplication.domain.Genre;
import com.example.movieapplication.domain.Movie;
import com.example.movieapplication.domain.MovieDetail;
import com.example.movieapplication.utils.DateFormat;

public class MovieDetailUI {

    public static void setMovie(Activity activity, MovieDetail movie, LinearLayout movieContainer, LinearLayout buttons) {

        buttons.setVisibility(View.GONE);
        movieContainer.setOrientation(LinearLayout.VERTICAL);
        movieContainer.setBackgroundColor(0xFF000000);

        // 1) 포스터 전체 영역
        ImageView posterImageView = new ImageView(activity);
        int imageWidth = MATCH_PARENT;
        int imageHeight = 1200;
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(imageWidth, imageHeight);
        imageParams.setMargins(10,30,10,10);
        posterImageView.setLayoutParams(imageParams);

        Glide.with(activity) .load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath()) .into(posterImageView);


        // 2) 하단 Info 영역
        LinearLayout infoLayout = new LinearLayout(activity);
        infoLayout.setOrientation(LinearLayout.VERTICAL);
        infoLayout.setPadding(32, 32, 32, 32);
        LinearLayout.LayoutParams infoParams =
                new LinearLayout.LayoutParams(imageWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        infoParams.setMargins(10, 0, 10, 10);
        infoLayout.setLayoutParams(infoParams);
        LinearLayout horizontalInfo1 = new LinearLayout(activity);
        horizontalInfo1.setOrientation(LinearLayout.HORIZONTAL);
        horizontalInfo1.setGravity(Gravity.CENTER);
        horizontalInfo1.setPadding(32, 32, 32, 32);

        LinearLayout horizontalInfo2 = new LinearLayout(activity);
        horizontalInfo2.setOrientation(LinearLayout.HORIZONTAL);
        horizontalInfo2.setGravity(Gravity.CENTER);
        horizontalInfo2.setPadding(32, 0, 32, 32);

        // 3) 텍스트들 생성
        TextView titleTextView = new TextView(activity);
        titleTextView.setText(movie.getTitle());
        titleTextView.setTextSize(26);
        titleTextView.setTextColor(0xFFFFFFFF);
        titleTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView releaseDateTextView = new TextView(activity);
        releaseDateTextView.setText(movie.getReleaseDate() + " 개봉 | ");
        releaseDateTextView.setTextSize(16);
        releaseDateTextView.setTextColor(0xFFDDDDDD);

        TextView runtimeTextView = new TextView(activity);
        runtimeTextView.setText(" \uD83D\uDD51" + movie.getRuntime() + "분 | ");
        runtimeTextView.setTextSize(16);
        runtimeTextView.setTextColor(0xFFDDDDDD);

        TextView rateTextView = new TextView(activity);
        rateTextView.setText("⭐ " + String.format("%.2f", movie.getVoteAverage()));
        rateTextView.setTextSize(16);
        rateTextView.setTextColor(0xFFFFFFFF);

        TextView overviewTextView = new TextView(activity);
        overviewTextView.setText(movie.getOverview());
        overviewTextView.setTextSize(18);
        overviewTextView.setTextColor(0xFFFFFFFF);

        int cnt = 0;
        for (Genre genre: movie.getGenres()){
            if (cnt == 3) break;
            TextView genreTextView = new TextView(activity);
            genreTextView.setText((cnt == 0 ? "[ " : "") + genre.getName() + (cnt == 2 ? " ]" : ", "));
            genreTextView.setTextSize(16);
            genreTextView.setTextColor(0xFFFFFFFF);
            horizontalInfo2.addView(genreTextView);
            cnt ++;
        }

        // 4) infoLayout에 추가
        infoLayout.addView(titleTextView);

        horizontalInfo1.addView(releaseDateTextView);
        horizontalInfo1.addView(runtimeTextView);
        horizontalInfo1.addView(rateTextView);
        infoLayout.addView(horizontalInfo1);
        infoLayout.addView(horizontalInfo2);

        infoLayout.addView(overviewTextView);

        // 5) 전체 컨테이너에 추가
        movieContainer.addView(posterImageView);
        movieContainer.addView(infoLayout);

        buttons.setVisibility(View.VISIBLE);
    }
}