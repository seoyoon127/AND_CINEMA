package com.example.movieapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.api.UseMovieDetailApi;
import com.example.movieapplication.components.NavigationBar;
import com.example.movieapplication.db.UserQuery;
import com.example.movieapplication.domain.User;
import com.google.android.material.appbar.MaterialToolbar;

public class LoginActivity extends AppCompatActivity {
    EditText emailInput, passwordInput;
    UserQuery userQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent prevIntent = getIntent();
        userQuery = new UserQuery(this);

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("로그인");

        NavigationBar.setNavigate(toolbar, this, prevIntent);

        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_bar, menu);
        return true;
    }

    public void moveToSignup(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        intent.putExtra("prevClass", this.getClass());
        startActivity(intent);
    }

    public void loginOnClick(View view){
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        User user = new User(email, password);

        Integer userId = userQuery.login(user);
        if (userId != -1) {
            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();
            SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("user_id", userId);
            editor.apply();
            finish();
        } else {
            Toast.makeText(this, "이메일이나 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
