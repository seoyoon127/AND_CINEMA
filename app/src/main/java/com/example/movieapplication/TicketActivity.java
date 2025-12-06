package com.example.movieapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.components.NavigationBar;
import com.example.movieapplication.components.TicketUI;
import com.example.movieapplication.db.UserTicketQuery;
import com.example.movieapplication.domain.Ticket;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class TicketActivity extends AppCompatActivity {
    List<Ticket> ticketList;
    MaterialButton recentBtn, allBtn;
    boolean recent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_check);

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("예매 내역");

        NavigationBar.setNavigate(toolbar, this, null);

        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        UserTicketQuery userTicketQuery = new UserTicketQuery(this);
        ticketList = userTicketQuery.getTickets(userId);

        recentBtn = findViewById(R.id.recentBtn);
        allBtn = findViewById(R.id.prevBtn);

        recent = true;
        TicketUI.setTicketList(this, ticketList, recent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_bar, menu);
        return true;
    }

    public void recentTickets(View view){
        recent = true;
        TicketUI.setButton(this, recentBtn, allBtn);
        TicketUI.setTicketList(this, ticketList, recent);
    }

    public void prevTickets(View view){
        recent = false;
        TicketUI.setButton(this, allBtn, recentBtn);
        TicketUI.setTicketList(this, ticketList, recent);
    }
}
