package com.example.movieapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.api.UseMovieDetailApi;
import com.example.movieapplication.components.NavigationBar;
import com.google.android.material.appbar.MaterialToolbar;

public class MyPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        Intent prevIntent = getIntent();

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("마이페이지");

        NavigationBar.setNavigate(toolbar, this, prevIntent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_bar, menu);

        MenuItem mypage = menu.findItem(R.id.mypageBtn);
        mypage.setVisible(false);

        return true;
    }

    public void moveToLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("prevClass", this.getClass());
        startActivity(intent);
    }

}
