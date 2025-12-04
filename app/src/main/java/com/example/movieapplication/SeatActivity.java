package com.example.movieapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.movieapplication.api.UseMovieDetailApi;
import com.example.movieapplication.components.NavigationBar;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.HashMap;
import java.util.Map;

public class SeatActivity extends AppCompatActivity {
    private int id;
    private String theater;
    private String date;
    private String time;
    private final Map<String, Integer> seatCountMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        Intent prevIntent = getIntent();
        id = prevIntent.getIntExtra("id",0);
        theater = prevIntent.getStringExtra("theater");
        date = prevIntent.getStringExtra("date");
        time = prevIntent.getStringExtra("time");

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("예매하기");
        NavigationBar.setNavigate(toolbar, this, null);

        // 영화 정보 UI
        LinearLayout movieContainer = findViewById(R.id.movieContainer);
        UseMovieDetailApi useMovieDetail = new UseMovieDetailApi(this);
        useMovieDetail.loadMovieInfo(id, movieContainer);

        ((TextView)findViewById(R.id.theaterText)).setText(theater);
        ((TextView)findViewById(R.id.dateText)).setText(date);
        ((TextView)findViewById(R.id.timeText)).setText(time);

        setupLabels();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_bar, menu);
         return true;
    }

    private void setupLabels() {
        setupSeatRow(R.id.seatRowAdult, "성인");
        setupSeatRow(R.id.seatRowTeen, "청소년");
        setupSeatRow(R.id.seatRowSenior, "경로");
        setupSeatRow(R.id.seatRowDisabled, "장애인");
    }

    private void setupSeatRow(int rowId, String label) {
        ConstraintLayout row = findViewById(rowId);

        TextView labelView = row.findViewById(R.id.label);
        TextView countText = row.findViewById(R.id.countText);
        Button plus = row.findViewById(R.id.plusBtn);
        Button minus = row.findViewById(R.id.minusBtn);

        labelView.setText(label);

        // 초기값 0 설정
        seatCountMap.put(label, 0);

        plus.setOnClickListener(v -> {
            int current = seatCountMap.get(label);
            current++;
            seatCountMap.put(label, current);
            countText.setText(String.valueOf(current));
        });

        minus.setOnClickListener(v -> {
            int current = seatCountMap.get(label);
            if (current > 0) current--;
            seatCountMap.put(label, current);
            countText.setText(String.valueOf(current));
        });
    }

    public void moveToPay(View view){
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("theater", theater);
        intent.putExtra("date", date);
        intent.putExtra("time", time);
        intent.putExtra("seatMap", new HashMap<>(seatCountMap)); // 좌석 수 전달
        startActivity(intent);
    }
}
