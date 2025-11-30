package com.example.movieapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "andCinema.db";
    private static final int DATABASE_VERSION = 3;
    private static DBHelper instance;

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //회원 테이블
        db.execSQL(
                "CREATE TABLE user (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "email TEXT, " +
                        "password TEXT" +
                        ")"
        );
        //좋아요,평점 테이블
        db.execSQL(
                "CREATE TABLE user_movie (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "user_id INTEGER, " +
                        "movie_id INTEGER, " +
                        "rate REAL, " +
                        "likes INTEGER DEFAULT 0, " +
                        "FOREIGN KEY(user_id) REFERENCES user(_id)" +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS likes");
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
}
