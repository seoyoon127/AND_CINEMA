package com.example.movieapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.api.UseMovieDetailApi;
import com.example.movieapplication.components.NavigationBar;
import com.example.movieapplication.db.UserMovieQuery;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class MovieDetailActivity extends AppCompatActivity {
    private int id;
    int userId;
    UserMovieQuery userMovieQuery;
    MaterialButton likeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent prevIntent = getIntent();
        id = prevIntent.getIntExtra("id",0);

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("상세 보기");

        NavigationBar.setNavigate(toolbar, this, prevIntent);

        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        userId = prefs.getInt("user_id", -1);

        UseMovieDetailApi useMovieDetail = new UseMovieDetailApi(this);
        useMovieDetail.loadMovie(id, findViewById(R.id.buttons));

        userMovieQuery = new UserMovieQuery(this);

        likeBtn = findViewById(R.id.likeBtn);
        checkLike();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_bar, menu);
        return true;
    }

    public void moveToCast(View view){
        Intent intent = new Intent(this, CreditActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void moveToBook(View view){
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        userId = prefs.getInt("user_id", -1);
        if(userId == -1){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "해당 서비스를 이용하려면 로그인해주세요",Toast.LENGTH_SHORT);
        } else{
            Intent intent = new Intent(this, BookingActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }

    public void likeOnClick(View view){
        userMovieQuery.likesClick(userId, id);
        checkLike();
    }

    public void checkLike(){
        int cur = userMovieQuery.getCurrentLike(userId, id);
        if (cur == 1){
            likeBtn.setText("♥\uFE0F");
        }else{
            likeBtn.setText("♡");
        }
    }
}
