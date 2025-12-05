package com.example.movieapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.api.UseMovieDetailApi;
import com.example.movieapplication.components.NavigationBar;
import com.example.movieapplication.data.MovieCost;
import com.example.movieapplication.db.UserTicketQuery;
import com.example.movieapplication.domain.Ticket;
import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class PayActivity extends AppCompatActivity {
    Map<String, Integer> costMap;
    Ticket ticket;
    int id;
    int clickedRes = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Intent prevIntent = getIntent();
        id = prevIntent.getIntExtra("id",0);
        String theater = prevIntent.getStringExtra("theater");
        String date = prevIntent.getStringExtra("date");
        String time = prevIntent.getStringExtra("time");
        Map<String, Integer> seatCountMap = (Map<String, Integer>) prevIntent.getSerializableExtra("seatMap");
        ArrayList<String> selectedSeats = getIntent().getStringArrayListExtra("selectedSeats");
        costMap = MovieCost.getMap();

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("결제");

        NavigationBar.setNavigate(toolbar, this, null);

        LinearLayout movieContainer = findViewById(R.id.movieContainer);
        UseMovieDetailApi useMovieDetail = new UseMovieDetailApi(this);
        useMovieDetail.loadMovieInfo(id, movieContainer);

        ((TextView)findViewById(R.id.theaterText)).setText(theater);
        ((TextView)findViewById(R.id.dateText)).setText(date);
        ((TextView)findViewById(R.id.timeText)).setText(time);
        String text = seatCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .reduce((s1, s2) -> s1 + ", " + s2)
                .orElse("");
        ((TextView)findViewById(R.id.peopleText)).setText(text);
        String seatsText = String.join(", ", selectedSeats);
        ((TextView)findViewById(R.id.seatText)).setText(seatsText);
        Integer cost= seatCountMap.entrySet().stream()
                .mapToInt(e -> e.getValue() * costMap.getOrDefault(e.getKey(), 0))
                .sum();
        ((TextView)findViewById(R.id.costText)).setText(String.valueOf(cost));

        ImageButton lpay = findViewById(R.id.lpay);
        ImageButton toss = findViewById(R.id.toss);
        ImageButton naver = findViewById(R.id.naver);

        // 공용 클릭 리스너
        View.OnClickListener payClickListener = v -> {
            ImageButton clicked = (ImageButton) v;

            // 모든 버튼 초기화 (회색 테두리)
            lpay.setBackgroundResource(R.drawable.border_gray);
            toss.setBackgroundResource(R.drawable.border_gray);
            naver.setBackgroundResource(R.drawable.border_gray);

            // 클릭한 버튼만 선택 상태 (빨간 테두리)
            clicked.setBackgroundResource(R.drawable.border_red);

            if (clicked == lpay) clickedRes = R.drawable.lpay;
            else if (clicked == toss) clickedRes = R.drawable.toss;
            else if (clicked == naver) clickedRes = R.drawable.naver;
        };

        lpay.setOnClickListener(payClickListener);
        toss.setOnClickListener(payClickListener);
        naver.setOnClickListener(payClickListener);

        Ticket newTicket = new Ticket();
        ticket = newTicket.createTicket(id,theater, date, time, seatCountMap, cost);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_bar, menu);
        return true;
    }

    public void moveToPayDone(View view){
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        if (userId != -1 && ticket != null){
            UserTicketQuery userTicketQuery = new UserTicketQuery(this);
            userTicketQuery.saveTicket(userId, id, ticket);
            Intent intent = new Intent(this, PayDoneActivity.class);
            intent.putExtra("img", clickedRes);
            startActivity(intent);
            Toast.makeText(this, "결제되었습니다.", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "정보가 맞는지 확인해주세요.", Toast.LENGTH_SHORT).show();
        }
    }
}
