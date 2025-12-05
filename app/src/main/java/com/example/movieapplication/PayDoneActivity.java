package com.example.movieapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PayDoneActivity extends AppCompatActivity {
        ImageView img;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pay_done);

            img = findViewById(R.id.payImg);
            Intent prevIntent = getIntent();
            int img_url = prevIntent.getIntExtra("img",0);
            img.setImageResource(img_url);
        }

    public void moveToMyPage(View view){
        Intent intent = new Intent(this, MyPageActivity.class);
        startActivity(intent);
    }

    public void moveToHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
