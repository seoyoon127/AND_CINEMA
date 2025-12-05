package com.example.movieapplication.utils;

import com.example.movieapplication.domain.Movie;
import com.example.movieapplication.domain.MovieDetail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormat {
    public static boolean isInRange(MovieDetail movie) {
        String releaseDateStr = movie.getReleaseDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date releaseDate = sdf.parse(releaseDateStr);

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.WEEK_OF_YEAR, -4);
            Date twoWeeksAgo = cal.getTime();

            return !releaseDate.before(twoWeeksAgo);

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
