package com.example.movieapplication.domain;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public class Ticket implements Serializable {
    private int movieId;
    private String theater;
    private String dateTime;
    private String jsonSeats;
    private Integer totalPrice;

    public Ticket() {
    }

    public Ticket(int movieId, String theater, String dateTime, String jsonSeats, Integer totalPrice) {
        this.movieId = movieId;
        this.theater = theater;
        this.dateTime = dateTime;
        this.jsonSeats = jsonSeats;
        this.totalPrice = totalPrice;
    }

    public Ticket createTicket(int movieId, String theater, String date, String time, Map<String, Integer> seatCountMap, Integer cost) {
        String dateTime = date + " " + time;

        JSONObject seatJson = new JSONObject();
        for (Map.Entry<String, Integer> entry : seatCountMap.entrySet()) {
            if (entry.getValue() > 0) {
                try {
                    seatJson.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        String jsonSeats = seatJson.toString();

        return new Ticket(movieId,theater, dateTime, jsonSeats, cost);
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

    public String getJsonSeats() {
        return jsonSeats;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }
}
