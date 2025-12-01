package com.example.movieapplication.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.movieapplication.domain.User;

public class UserQuery {
    private DBHelper helper;
    private SQLiteDatabase db;

    public UserQuery(Context context) {
        DBHelper helper = DBHelper.getInstance(context);
        db = helper.getWritableDatabase();
    }
    public Integer signup(User user){
        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());

        long id = db.insert("user", null, values);
        return (int) id;
    }

    public boolean userExist(String email){
        String sql = "SELECT * FROM user WHERE email = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{email});

        boolean exists = cursor.getCount() == 1;
        cursor.close();
        return exists;
    }
    
    public Integer login(User user){
        String sql = "SELECT _id FROM user WHERE email = ? AND password = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{user.getEmail(), user.getPassword()});

        Integer userId = -1;

        if (cursor.moveToFirst()) {
            int idx = cursor.getColumnIndex("_id");
            if (idx != -1) {
                userId = cursor.getInt(idx);
            }
        }
        System.out.println("USERID: " + userId);
        Log.d("DB_LOGIN", "login returned: " + userId);

        cursor.close();
        return userId;
    }
    public User getUserInfo(Integer userId){
        String sql = "SELECT * FROM user WHERE _id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(userId)});

        User user = null;
        if (cursor.moveToFirst()) {
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            user = new User(email, password);
        }

        cursor.close();
        return user;
    }
}
