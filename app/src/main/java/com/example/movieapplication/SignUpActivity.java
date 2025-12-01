package com.example.movieapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.components.NavigationBar;
import com.example.movieapplication.db.UserQuery;
import com.example.movieapplication.domain.User;
import com.google.android.material.appbar.MaterialToolbar;

public class SignUpActivity extends AppCompatActivity {
    EditText emailInput, passwordInput;
    UserQuery userQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Intent prevIntent = getIntent();
        userQuery = new UserQuery(this);

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("회원가입");

        NavigationBar.setNavigate(toolbar, this, prevIntent);

        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_bar, menu);
        return true;
    }

    public void signupOnClick(View view){
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (userQuery.userExist(email)) {
            Toast.makeText(this, "이미 존재하는 이메일입니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User(email, password);
        long result = userQuery.signup(user);

        if (result > 0) {
            Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show();
        }
    }
}
