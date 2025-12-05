package com.example.movieapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.api.UseCreditApi;
import com.example.movieapplication.api.UseMovieDetailApi;
import com.example.movieapplication.components.NavigationBar;
import com.google.android.material.appbar.MaterialToolbar;

public class CreditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        Intent prevIntent = getIntent();
        int id = prevIntent.getIntExtra("id",0);

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);
        UseMovieDetailApi useMovieDetail = new UseMovieDetailApi(this);
        useMovieDetail.loadMovieName(id, name -> {
            getSupportActionBar().setTitle(name);
        });

        NavigationBar.setNavigate(toolbar, this, null);

        UseCreditApi useCredit = new UseCreditApi(this);
        useMovieDetail.loadBackdropPath(id, backdrop -> {
            useCredit.loadCredits(id, backdrop);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_bar, menu);
        return true;
    }
}
