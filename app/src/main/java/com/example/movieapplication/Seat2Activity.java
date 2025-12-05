package com.example.movieapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.components.NavigationBar;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seat2Activity extends AppCompatActivity {
    private int id;
    private String theater;
    private String date;
    private String time;
    private Map<String, Integer> seatCountMap;
    private List<String> selectedSeats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat2);

        Intent prevIntent = getIntent();
        id = prevIntent.getIntExtra("id",0);
        theater = prevIntent.getStringExtra("theater");
        date = prevIntent.getStringExtra("date");
        time = prevIntent.getStringExtra("time");
        seatCountMap = (Map<String, Integer>) prevIntent.getSerializableExtra("seatMap");

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("예매하기");

        NavigationBar.setNavigate(toolbar, this, null);


        ((TextView)findViewById(R.id.theaterText)).setText(theater);
        ((TextView)findViewById(R.id.dateText)).setText(date);
        ((TextView)findViewById(R.id.timeText)).setText(time);

        setupSeatGrid();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_bar, menu);
        return true;
    }

    private void setupSeatGrid() {
        GridLayout container = findViewById(R.id.seatContainer);

        int columns = 12;
        String rows = "ABCDEFGHIJ";

        container.setColumnCount(columns);
        container.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        container.setUseDefaultMargins(true);

        int size_w = dpToPx(55);
        int size_h = dpToPx(45);

        for (char row : rows.toCharArray()) {
            for (int col = 1; col <= columns; col++) {

                String seatName = String.format("%c%02d", row, col);

                Button seatBtn = new Button(this);
                seatBtn.setText(seatName);
                seatBtn.setTextSize(15);
                seatBtn.setBackground(getDrawable(R.drawable.seat_selector));

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = size_w;
                params.height = size_h;
                seatBtn.setLayoutParams(params);
                seatBtn.setSingleLine(true);

                seatBtn.setOnClickListener(v -> {
                    v.setSelected(!v.isSelected());
                    seatBtn.setTextColor(v.isSelected()? Color.WHITE : Color.BLACK);
                    if (v.isSelected()) {
                        if (!selectedSeats.contains(seatName)) selectedSeats.add(seatName);
                    } else {
                        selectedSeats.remove(seatName);
                    }
                });

                container.addView(seatBtn);
            }
        }
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public void moveToPay(View view){
        int totalSeats = seatCountMap.values().stream().mapToInt(Integer::intValue).sum();
        if (selectedSeats.size() == totalSeats){
            Intent intent = new Intent(this, PayActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("theater", theater);
            intent.putExtra("date", date);
            intent.putExtra("time", time);
            intent.putExtra("seatMap", new HashMap<>(seatCountMap));
            intent.putStringArrayListExtra("selectedSeats", new ArrayList<>(selectedSeats));
            startActivity(intent);   
        } else {
            Toast.makeText(this, totalSeats + "좌석을 선택해주세요.", Toast.LENGTH_SHORT).show();
        }
    }
}
