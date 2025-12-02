package com.example.movieapplication.components;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapplication.R;
import com.example.movieapplication.domain.Credit;

public class CreditUI {
    public static void setBackdropPath(Activity activity, String backdropPath, ImageView imageView){

        // 가로 포스터
        int imageWidth = MATCH_PARENT;
        int imageHeight = 600;
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(imageWidth, imageHeight);
        imageParams.setMargins(10,30,10,10);
        imageView.setLayoutParams(imageParams);

        Glide.with(activity) .load("https://image.tmdb.org/t/p/w500" + backdropPath) .into(imageView);
    }

    public static void setCreditInfo(Activity activity, Credit credit){
        // 감독, 캐스트, 제작진, 시각 효과
        LinearLayout targetLayout = null;
        String department = credit.getKnownForDepartment();
        targetLayout = getLinearLayout(activity, department);

        // 동적 카드 생성
        LinearLayout card = new LinearLayout(activity);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setGravity(View.TEXT_ALIGNMENT_CENTER);
        card.setPadding(20, 20, 20, 20);

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        cardParams.setMargins(20, 0, 20, 0);
        card.setLayoutParams(cardParams);

        // 1) 프로필 이미지 (원형)
        ImageView profile = new ImageView(activity);
        LinearLayout.LayoutParams imgParams =
                new LinearLayout.LayoutParams(200, 200);
        profile.setLayoutParams(imgParams);

        Glide.with(activity)
                .load("https://image.tmdb.org/t/p/w200" + credit.getProfilePath())
                .circleCrop() // 원형 이미지
                .into(profile);

        // 2) 이름
        TextView name = new TextView(activity);
        name.setText(credit.getName());
        name.setTextSize(14);
        name.setTextColor(Color.WHITE);
        name.setPadding(0, 10, 0, 0);

        // 3) 역할 또는 직책
        TextView role = new TextView(activity);
        role.setText(credit.getCharacter() != null
                ? credit.getCharacter()
                : "");
        role.setTextSize(12);
        role.setTextColor(Color.WHITE);
        role.setPadding(0, 5, 0, 0);

        // 카드에 붙이기
        card.addView(profile);
        card.addView(name);
        card.addView(role);

        // department에 맞는 layout에 붙이기
        targetLayout.addView(card);
    }

    private static LinearLayout getLinearLayout(Activity activity, String department) {
        LinearLayout targetLayout;
        switch (department) {
            case "Acting":
                targetLayout = activity.findViewById(R.id.castContainer);
                break;

            case "Directing":
                targetLayout = activity.findViewById(R.id.directorContainer);
                break;

            case "Visual Effects":
                targetLayout = activity.findViewById(R.id.effectContainer);
                break;

            case "Crew":
            default:
                targetLayout = activity.findViewById(R.id.crewContainer);
                break;
        }
        return targetLayout;
    }

    public static void setCreditNull(Activity activity, LinearLayout layout){
        TextView emptyText = new TextView(activity);
        emptyText.setText("해당 정보가 없습니다");
        emptyText.setTextSize(16);
        emptyText.setTextColor(Color.WHITE);
        emptyText.setPadding(20, 20, 20, 20);
        emptyText.setGravity(Gravity.CENTER);

        layout.addView(emptyText);
    }
}