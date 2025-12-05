package com.example.movieapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.api.UseMovieDetailApi;
import com.example.movieapplication.components.NavigationBar;
import com.example.movieapplication.db.UserQuery;
import com.example.movieapplication.domain.User;
import com.google.android.material.appbar.MaterialToolbar;

public class MyPageActivity extends AppCompatActivity {
    UserQuery userQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        Intent prevIntent = getIntent();
        userQuery = new UserQuery(this);

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("마이페이지");

        NavigationBar.setNavigate(toolbar, this, prevIntent);
        setUIByLogin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUIByLogin();
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

    private void setUIByLogin() {
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        LinearLayout loggedInLayout = findViewById(R.id.loggedInLayout);
        LinearLayout loggedOutLayout = findViewById(R.id.loggedOutLayout);
        if (userId != -1) {
            loggedInLayout.setVisibility(View.VISIBLE);
            loggedOutLayout.setVisibility(View.GONE);
            setLoggedInInfoToUI(userId);

        } else {
            loggedInLayout.setVisibility(View.GONE);
            loggedOutLayout.setVisibility(View.VISIBLE);
        }
    }

    private void setLoggedInInfoToUI(Integer userId) {
        TextView userNameText = findViewById(R.id.userNameText);
        User user = userQuery.getUserInfo(userId);
        userNameText.setText(user.getEmail().split("@")[0] + "님, 반가워요!");
    }

    public void logoutOnClick(View view){
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        prefs.edit().remove("user_id").apply();
        
        int userId = prefs.getInt("user_id", -1);
        if (userId == -1){
            Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show();
            setUIByLogin();
        } else{
            Toast.makeText(this, "로그아웃 실패", Toast.LENGTH_SHORT).show();
        }
        
    }

    public void ticketOnClick(View view){
        Intent intent = new Intent(this, TicketActivity.class);
        startActivity(intent);
    }

    public void likeListOnClick(View view){
        Intent intent= new Intent(this, LikesActivity.class);
        startActivity(intent);
    }
}
