package com.example.movieapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.movieapplication.domain.Ticket;
import com.example.movieapplication.domain.User;

import org.json.JSONArray;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserTicketQuery {
    private DBHelper helper;
    private SQLiteDatabase db;

    public UserTicketQuery(Context context) {
        this.helper = DBHelper.getInstance(context);
        this.db = helper.getWritableDatabase();
    }

    public void saveTicket(Integer userId, Integer movieId, Ticket ticket){
        JSONArray jsonArray = new JSONArray(ticket.getSeatList());
        String seatJson = jsonArray.toString();  // ["A1","A2"]

        db.execSQL(
                "INSERT INTO user_ticket (user_id, movie_id, theater, time, seats, total_price) VALUES (?, ?, ?, ?, ?, ?)",
                new Object[]{
                        userId,
                        movieId,
                        ticket.getTheater(),
                        ticket.getDateTime(),
                        seatJson,
                        ticket.getTotalPrice()
                }
        );
    }

    public List<Ticket> getTickets(Integer userId) {
        String sql = "SELECT * FROM user_ticket WHERE user_id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(userId)});
        List<Ticket> tickets = new ArrayList<>();

        while (cursor.moveToNext()) {

            int movieId = cursor.getInt(cursor.getColumnIndexOrThrow("movie_id"));
            String theater = cursor.getString(cursor.getColumnIndexOrThrow("theater"));
            String dateTime = cursor.getString(cursor.getColumnIndexOrThrow("time"));
            String seatsJson = cursor.getString(cursor.getColumnIndexOrThrow("seats"));
            int totalPrice = cursor.getInt(cursor.getColumnIndexOrThrow("total_price"));

            // JSON → List<String>
            List<String> seatList = new ArrayList<>();
            try {
                JSONArray arr = new JSONArray(seatsJson);
                for (int i = 0; i < arr.length(); i++) {
                    seatList.add(arr.getString(i));
                }
            } catch (Exception e) { }

            Ticket ticket = new Ticket(movieId, theater, dateTime, seatList, totalPrice);
            tickets.add(ticket);
        }

        cursor.close();
        return tickets;
    }
}
