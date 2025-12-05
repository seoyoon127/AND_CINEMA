package com.example.movieapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.components.LikesUI;
import com.example.movieapplication.components.NavigationBar;
import com.example.movieapplication.db.UserMovieQuery;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class LikesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes);

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("좋아요 목록");

        NavigationBar.setNavigate(toolbar, this, null);

        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        UserMovieQuery userMovieQuery = new UserMovieQuery(this);
        List<Integer> movieIdList = userMovieQuery.getAllLikes(userId);

        LikesUI.setLikesList(this, movieIdList);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_bar, menu);
        return true;
    }

}
