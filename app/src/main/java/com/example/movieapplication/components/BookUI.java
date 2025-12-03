package com.example.movieapplication.components;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapplication.domain.Genre;
import com.example.movieapplication.domain.MovieDetail;
import com.google.android.flexbox.FlexboxLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookUI {
    public static void setMovie(Activity activity, MovieDetail movie, LinearLayout movieContainer) {

        movieContainer.setOrientation(LinearLayout.HORIZONTAL);
        movieContainer.setGravity(Gravity.CENTER_VERTICAL);

        // 1) 포스터 전체 영역
        ImageView posterImageView = new ImageView(activity);
        int imageWidth = 240;
        int imageHeight = 360;
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(imageWidth, imageHeight);
        imageParams.setMargins(10,30,10,10);
        posterImageView.setLayoutParams(imageParams);

        Glide.with(activity) .load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath()) .into(posterImageView);


        // 2) 우측 Info 영역
        LinearLayout infoLayout = new LinearLayout(activity);
        infoLayout.setOrientation(LinearLayout.VERTICAL);
        infoLayout.setPadding(32, 32, 32, 32);
        LinearLayout.LayoutParams infoParams =
                new LinearLayout.LayoutParams(0, imageHeight, 1f);
        infoParams.setMargins(10, 20, 10, 10);
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
        titleTextView.setTextSize(22);
        titleTextView.setTypeface(null, Typeface.BOLD);
        titleTextView.setGravity(Gravity.CENTER);

        TextView releaseDateTextView = new TextView(activity);
        releaseDateTextView.setText(movie.getReleaseDate() + " 개봉 | ");
        releaseDateTextView.setTextSize(16);

        TextView runtimeTextView = new TextView(activity);
        runtimeTextView.setText(movie.getRuntime() + "분");
        runtimeTextView.setTextSize(16);

        TextView overviewTextView = new TextView(activity);
        overviewTextView.setText(movie.getOverview());
        overviewTextView.setTextSize(18);
        overviewTextView.setTextColor(0xFFFFFFFF);

        int cnt = 0;
        for (Genre genre: movie.getGenres()){
            if (cnt == 3) break;
            TextView genreTextView = new TextView(activity);
            genreTextView.setText(genre.getName() + (cnt == 2 ? "" : ", "));
            genreTextView.setTextSize(16);
            horizontalInfo2.addView(genreTextView);
            cnt ++;
        }

        // 4) infoLayout에 추가
        infoLayout.addView(titleTextView);

        horizontalInfo1.addView(releaseDateTextView);
        horizontalInfo1.addView(runtimeTextView);
        infoLayout.addView(horizontalInfo1);
        infoLayout.addView(horizontalInfo2);

        infoLayout.addView(overviewTextView);

        // 5) 전체 컨테이너에 추가
        movieContainer.addView(posterImageView);
        movieContainer.addView(infoLayout);

    }

    public interface OnDateSelectedListener {
        void onDateSelected(String date); // yyyy-MM-dd 형식
    }

    public static void setDate(Activity activity, FlexboxLayout dateContainer, OnDateSelectedListener listener) {
        dateContainer.removeAllViews(); // 기존 내용 초기화

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dayFormat = new SimpleDateFormat("d", Locale.KOREA);
        SimpleDateFormat weekFormat = new SimpleDateFormat("EEE", Locale.KOREA);
        SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

        final int boxSize = dpToPx(activity, 60); // 날짜 박스 사이즈
        final int margin = dpToPx(activity, 8);

        for (int i = 0; i < 7; i++) {
            LinearLayout dateBox = new LinearLayout(activity);
            dateBox.setOrientation(LinearLayout.VERTICAL);
            dateBox.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(boxSize, boxSize);
            params.setMargins(margin, margin, margin, margin);
            dateBox.setLayoutParams(params);

            // 배경: 회색, border radius 10dp
            GradientDrawable background = new GradientDrawable();
            background.setColor(Color.LTGRAY);
            background.setCornerRadius(dpToPx(activity, 10));
            dateBox.setBackground(background);

            // 숫자
            TextView dayText = new TextView(activity);
            dayText.setText(dayFormat.format(calendar.getTime()));
            dayText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            dayText.setTypeface(null, android.graphics.Typeface.BOLD);
            dayText.setGravity(Gravity.CENTER);

            // 요일
            TextView weekText = new TextView(activity);
            weekText.setText(weekFormat.format(calendar.getTime()));
            weekText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            weekText.setGravity(Gravity.CENTER);

            dateBox.addView(dayText);
            dateBox.addView(weekText);

            // 클릭 시 빨간색으로 변경 + 날짜 리턴
            final String selectedDate = fullFormat.format(calendar.getTime());
            dateBox.setOnClickListener(v -> {
                // 모든 자식 초기화
                for (int j = 0; j < dateContainer.getChildCount(); j++) {
                    View child = dateContainer.getChildAt(j);
                    GradientDrawable bg = (GradientDrawable) child.getBackground();
                    bg.setColor(Color.LTGRAY);
                    child.setBackground(bg);

                    if (child instanceof LinearLayout) {
                        LinearLayout layout = (LinearLayout) child;
                        for (int k = 0; k < layout.getChildCount(); k++) {
                            if (layout.getChildAt(k) instanceof TextView) {
                                ((TextView) layout.getChildAt(k)).setTextColor(Color.BLACK); // 기본 글자색
                            }
                        }
                    }
                }
                // 선택된 박스 빨간색
                background.setColor(Color.parseColor("#EA3F46"));
                dateBox.setBackground(background);
                for (int k = 0; k < dateBox.getChildCount(); k++) {
                    if (dateBox.getChildAt(k) instanceof TextView) {
                        ((TextView) dateBox.getChildAt(k)).setTextColor(Color.WHITE);
                    }
                }

                if (listener != null) {
                    listener.onDateSelected(selectedDate); // 선택된 날짜 전달
                }
            });

            dateContainer.addView(dateBox);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    public interface OnTimeSelectedListener {
        void onTimeSelected(String time);
    }

    public static void setTimeListener(FlexboxLayout timeContainer, OnTimeSelectedListener listener) {
        for (int i = 0; i < timeContainer.getChildCount(); i++) {
            LinearLayout timeBox = (LinearLayout) timeContainer.getChildAt(i);
            TextView hourText = (TextView) timeBox.getChildAt(0);
            String timeStr = hourText.getText().toString();

            final String selectedTime = timeStr;
            timeBox.setOnClickListener(v -> {
                // 색상 초기화
                for (int j = 0; j < timeContainer.getChildCount(); j++) {
                    LinearLayout tb = (LinearLayout) timeContainer.getChildAt(j);
                    GradientDrawable bg = (GradientDrawable) tb.getBackground();
                    bg.setColor(Color.LTGRAY);
                    tb.setBackground(bg);
                }
                // 선택된 박스 색상 변경
                GradientDrawable bg = (GradientDrawable) timeBox.getBackground();
                bg.setColor(Color.parseColor("#EA3F46"));
                timeBox.setBackground(bg);

                if (listener != null) listener.onTimeSelected(selectedTime);
            });
        }
    }

    public static void setTime(Activity activity, FlexboxLayout timeContainer, int runtime, OnTimeSelectedListener listener) {
        timeContainer.removeAllViews();

        for (int hour = 10; hour <= 24; hour += 3) {
            LinearLayout timeBox = new LinearLayout(activity);
            timeBox.setOrientation(LinearLayout.VERTICAL);
            timeBox.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dpToPx(activity, 60), dpToPx(activity, 60));
            params.setMargins(dpToPx(activity, 4), dpToPx(activity, 4), dpToPx(activity, 4), dpToPx(activity, 4));
            timeBox.setLayoutParams(params);

            GradientDrawable background = new GradientDrawable();
            background.setColor(Color.LTGRAY);
            background.setCornerRadius(dpToPx(activity, 10));
            timeBox.setBackground(background);

            // 시간 표시
            TextView hourText = new TextView(activity);
            hourText.setText(hour + "시");
            hourText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
            hourText.setTypeface(null, Typeface.BOLD);
            hourText.setGravity(Gravity.CENTER);
            timeBox.addView(hourText);

            // 종료 시간 표시 (runtime)
            TextView endText = new TextView(activity);
            int endHour = hour + runtime / 60;
            endText.setText("~ " + endHour + "시");
            endText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            endText.setTextColor(Color.GRAY);
            endText.setGravity(Gravity.CENTER);
            timeBox.addView(endText);

            // 클릭 이벤트
            final String selected = hour + "시";
            timeBox.setOnClickListener(v -> {
                // 초기화
                for (int i = 0; i < timeContainer.getChildCount(); i++) {
                    LinearLayout child = (LinearLayout) timeContainer.getChildAt(i);
                    ((GradientDrawable) child.getBackground()).setColor(Color.LTGRAY);
                }
                background.setColor(Color.parseColor("#EA3F46"));
                timeBox.setBackground(background);

                if (listener != null) listener.onTimeSelected(selected);
            });

            timeContainer.addView(timeBox);
        }
    }

    private static int dpToPx(Context context, int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
