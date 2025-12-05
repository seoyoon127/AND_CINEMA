package com.example.movieapplication.domain;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Ticket implements Serializable {
    private int movieId;
    private String theater;
    private String dateTime;
    private List<String> seatList;
    private Integer totalPrice;

    public Ticket() {
    }

    public Ticket(int movieId, String theater, String dateTime,  List<String> seatList, Integer totalPrice) {
        this.movieId = movieId;
        this.theater = theater;
        this.dateTime = dateTime;
        this.seatList = seatList;
        this.totalPrice = totalPrice;
    }

    public Ticket createTicket(int movieId, String theater, String date, String time, List<String> seatList, Integer cost) {
        String dateTime = date + " " + time;
        return new Ticket(movieId,theater, dateTime, seatList, cost);
    }

    public int getMovieId() {
        return movieId;
    }

    public String getTheater() {
        return theater;
    }

    public String getDateTime() {
        return dateTime;
    }
    public List<String> getSeatList() {
        return seatList;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }
}
