package com.example.movieapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.movieapplication.api.UseNowPlayingApi;
import com.example.movieapplication.components.NavigationBar;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("AND CINEMA");
        }

        NavigationBar.setNavigate(toolbar, this);

        UseNowPlayingApi useNowPlaying = new UseNowPlayingApi(this);
        useNowPlaying.loadMovies();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_bar, menu);
        return true;
    }
}
