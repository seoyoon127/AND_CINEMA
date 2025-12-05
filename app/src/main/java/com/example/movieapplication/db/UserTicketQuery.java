package com.example.movieapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.movieapplication.domain.Ticket;
import com.example.movieapplication.domain.User;

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
        db.execSQL(
                "INSERT INTO user_ticket (user_id, movie_id, theater, time, seats, total_price) VALUES (?, ?, ?, ?, ?, ?)",
                new Object[]{userId, movieId, ticket.getTheater(), ticket.getDateTime(), ticket.getJsonSeats(), ticket.getTotalPrice()}
        );
    }

    public List<Ticket> getTickets(Integer userId){

        String sql = "SELECT * FROM user_ticket WHERE user_id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(userId)});
        List<Ticket> tickets = new ArrayList<>();

        while (cursor.moveToNext()) {

            int movieId = cursor.getInt(cursor.getColumnIndexOrThrow("movie_id"));

            String theater = cursor.getString(cursor.getColumnIndexOrThrow("theater"));

            String dateTime = cursor.getString(cursor.getColumnIndexOrThrow("time"));

            String jsonSeats = cursor.getString(cursor.getColumnIndexOrThrow("seats"));

            int totalPrice = cursor.getInt(cursor.getColumnIndexOrThrow("total_price"));

            Ticket ticket = new Ticket(movieId, theater, dateTime, jsonSeats, totalPrice);
            tickets.add(ticket);
        }

        cursor.close();
        return tickets;
    }
}
