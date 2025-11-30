package com.example.movieapplication.db;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.movieapplication.domain.User;

public class UserQuery {
    private DBHelper helper;
    private SQLiteDatabase db;

    public UserQuery(DBHelper helper){
        this.helper = helper;
        this.db = helper.getWritableDatabase();
    }
    public Integer signup(User user){
        if (userExist(user)){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());

        long id = db.insert("user", null, values);
        return (int) id;
    }

    public boolean userExist(User user){
        String sql = "SELECT * FROM user WHERE email = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{user.getEmail()});

        boolean exists = cursor.getCount() == 1;
        cursor.close();
        return exists;
    }
    
    public void login(User user){
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{user.getEmail(), user.getPassword()});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        if (!exists){
            throw new IllegalArgumentException("이메일이나 비밀번호가 올바르지 않습니다.");
        }
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
