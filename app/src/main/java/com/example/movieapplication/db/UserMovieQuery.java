package com.example.movieapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.movieapplication.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserMovieQuery {
    private DBHelper helper;
    private SQLiteDatabase db;

    public UserMovieQuery(Context context) {
        DBHelper helper = DBHelper.getInstance(context);
        db = helper.getWritableDatabase();
    }
    public void likesClick(Integer userId, Integer movieId){
        if (userMovieExist(userId, movieId)) {

            int current = getCurrentLike(userId, movieId);
            int newLike = (current == 1) ? 0 : 1;
            updateLike(userId, movieId, newLike);

        } else {
            insertUserMovie(userId, movieId, null);
        }
    }

    public void rateClick(Integer userId, Integer movieId, Float rate){
        if (userMovieExist(userId, movieId)) {
            updateRate(userId, movieId, rate);

        } else {
            insertUserMovie(userId, movieId, rate);
        }
    }

    private boolean userMovieExist(Integer userId, Integer movieId) {
        String sql = "SELECT * FROM user_movie WHERE user_id = ? AND movie_id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{
                String.valueOf(userId),
                String.valueOf(movieId)
        });

        boolean exists = cursor.getCount() == 1;
        cursor.close();
        return exists;
    }

    public int getCurrentLike(Integer userId, Integer movieId) {
        String sql = "SELECT likes FROM user_movie WHERE user_id = ? AND movie_id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(userId), String.valueOf(movieId)});

        int like = 0;
        if (cursor.moveToFirst()) {
            int idx = cursor.getColumnIndex("likes");
            if(idx != -1) like = cursor.getInt(idx);
        }

        cursor.close();
        return like;
    }

    public List<Integer> getAllLikes(Integer userId) {
        String sql = "SELECT movie_id FROM user_movie WHERE user_id = ? AND likes = 1";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(userId)});
        List<Integer> movieIdList = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                int idx = cursor.getColumnIndex("movie_id");
                if(idx != -1){
                    movieIdList.add(cursor.getInt(idx));
                }
            } while(cursor.moveToNext());
        }

        cursor.close();
        return movieIdList;
    }

    private void updateLike(Integer userId, Integer movieId, int newLike) {
        String sql = "UPDATE user_movie SET likes = ? WHERE user_id = ? AND movie_id = ?";
        db.execSQL(sql, new Object[]{newLike, userId, movieId});
    }

    private void insertUserMovie(Integer userId, Integer movieId, Float rate) {
        String sql = "INSERT INTO user_movie (user_id, movie_id, likes, rate) VALUES (?, ?, ?, ?)";
        db.execSQL(sql, new Object[]{userId, movieId, 1, rate});
    }

    private void updateRate(Integer userId, Integer movieId, float newRate) {
        String sql = "UPDATE user_movie SET rate = ? WHERE user_id = ? AND movie_id = ?";
        db.execSQL(sql, new Object[]{newRate, userId, movieId});
    }

}
