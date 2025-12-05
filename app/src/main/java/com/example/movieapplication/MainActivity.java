package com.example.movieapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.movieapplication.api.UseMovieApi;
import com.example.movieapplication.components.NavigationBar;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {
    UseMovieApi movieApi;
    TextView sortText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        toolbar.setTitleTextAppearance(this, R.style.RedTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("AND CINEMA");

        NavigationBar.setNavigate(toolbar, this, null);

        movieApi = new UseMovieApi(this);
        movieApi.loadMovies("now_playing");


        sortText = findViewById(R.id.sortText);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_bar, menu);

        MenuItem back = menu.findItem(R.id.backBtn);
        MenuItem home = menu.findItem(R.id.homeBtn);
        back.setVisible(false);
        home.setVisible(false);

        return true;
    }

    public void sortBtnClick(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.sort_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nowPlayingMenu) {
                    movieApi.loadMovies("now_playing");
                    sortText.setText(item.getTitle());
                    return true;
                } else if (id == R.id.popularMenu) {
                    movieApi.loadMovies("popular");
                    sortText.setText(item.getTitle());
                    return true;
                } else if (id == R.id.topRatedMenu) {
                    movieApi.loadMovies("top_rated");
                    sortText.setText(item.getTitle());
                    return true;
                } else if (id == R.id.upcomingMenu) {
                    movieApi.loadMovies("upcoming");
                    sortText.setText(item.getTitle());
                    return true;
                }

                return false;
            }
        });

        popup.show();
    }
}
