package com.example.movieapplication.components;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapplication.MovieDetailActivity;
import com.example.movieapplication.domain.Movie;
import com.example.movieapplication.domain.MovieDetail;
import com.example.movieapplication.utils.DateFormat;

public class MovieDetailUI {
    public static void setMovie(Activity activity, MovieDetail movie, LinearLayout movieContainer){
        // 1) 영화마다 LinearLayout 생성 (세로로 이미지+텍스트)
        LinearLayout movieLayout = new LinearLayout(activity);
        LinearLayout infoLayout = new LinearLayout(activity);
        movieLayout.setOrientation(LinearLayout.HORIZONTAL);
        infoLayout.setOrientation(LinearLayout.VERTICAL);
        movieLayout.setPadding(8, 8, 8, 8);

        // 2) ImageView 생성
        ImageView posterImageView = new ImageView(activity);
        int imageWidth = 300; // px 단위, 필요하면 dp->px 변환
        int imageHeight = 450;
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(imageWidth, imageHeight);
        posterImageView.setLayoutParams(imageParams);

        // Glide로 이미지 로딩
        Glide.with(activity)
                .load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                .into(posterImageView);

        // 3) TextView 생성
        TextView titleTextView = new TextView(activity);
        titleTextView.setText(movie.getTitle());
        titleTextView.setTextSize(20);
        titleTextView.setSingleLine(true);
        titleTextView.setEllipsize(TextUtils.TruncateAt.END);
        titleTextView.setPadding(16, 0, 0, 0);

        TextView releaseDateTextView = new TextView(activity);
        releaseDateTextView.setText(String.valueOf(movie.getReleaseDate()));
        releaseDateTextView.setTextSize(16);
        releaseDateTextView.setPadding(16, 0, 0, 0);

        TextView rateTextView = new TextView(activity);
        rateTextView.setText("⭐" + String.format("%.2f",movie.getVoteAverage()));
        rateTextView.setTextSize(16);
        rateTextView.setPadding(16, 0, 0, 0);

        // 4) LinearLayout에 ImageView, TextView 추가
        infoLayout.addView(titleTextView);
        infoLayout.addView(releaseDateTextView);
        infoLayout.addView(rateTextView);
        movieLayout.addView(posterImageView);
        movieLayout.addView(infoLayout);


        // 5) movieLayout 클릭 이벤트 추가
        movieLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, MovieDetailActivity.class);
                intent.putExtra("id",movie.getId());
                activity.startActivity(intent);
            }
        });

        // 6) 최상위 LinearLayout(movieContainer)에 추가
        movieContainer.addView(movieLayout);
    }
}
