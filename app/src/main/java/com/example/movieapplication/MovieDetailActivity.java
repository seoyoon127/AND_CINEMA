package com.example.movieapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.api.UseMovieDetailApi;
import com.example.movieapplication.components.NavigationBar;
import com.google.android.material.appbar.MaterialToolbar;

public class MovieDetailActivity extends AppCompatActivity {
    private int id;
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

        UseMovieDetailApi useMovieDetail = new UseMovieDetailApi(this);
        useMovieDetail.loadMovie(id, findViewById(R.id.buttons));
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
        Intent intent = new Intent(this, BookingActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
