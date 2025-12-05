package com.example.movieapplication.components;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.movieapplication.MovieDetailActivity;
import com.example.movieapplication.R;
import com.example.movieapplication.api.UseMovieDetailApi;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

public class LikesUI {

    public static void setLikesList(AppCompatActivity activity, List<Integer> movieIdList) {

        FlexboxLayout container = activity.findViewById(R.id.likesContainer);
        container.removeAllViews();

        for (int id : movieIdList) {

            // 1) 아이템용 LinearLayout
            LinearLayout itemLayout = new LinearLayout(activity);
            LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            itemParams.setMargins(12, 12, 12, 12);
            itemLayout.setLayoutParams(itemParams);
            itemLayout.setOrientation(LinearLayout.VERTICAL);

            // 2) 포스터 ImageView
            ImageView posterImg = new ImageView(activity);
            LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(200, 300);
            posterImg.setLayoutParams(imgParams);
            posterImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
            itemLayout.addView(posterImg);

            // 3) Glide로 이미지 불러오기
            UseMovieDetailApi api = new UseMovieDetailApi(activity);
            api.loadMoviePoster(id, url -> {
                if (url != null) {
                    Glide.with(activity).load(url).into(posterImg);
                }
            });

            // 4) 클릭 시 MovieDetailActivity 열기
            posterImg.setOnClickListener(view -> {
                Intent intent = new Intent(activity, MovieDetailActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("prevClass", activity.getClass());
                activity.startActivity(intent);
            });

            // 5) FlexboxLayout에 추가
            container.addView(itemLayout);
        }
    }
}