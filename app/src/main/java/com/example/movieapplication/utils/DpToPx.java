package com.example.movieapplication.utils;

import android.app.Activity;

public class DpToPx {
    public static int convert(Activity activity, int dp) {
        float density = activity.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
