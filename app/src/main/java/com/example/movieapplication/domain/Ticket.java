package com.example.movieapplication.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Ticket implements Serializable {
    private String theater;
    private String dateTime;
    private String jsonSeats;
    private Integer totalPrice;

    public Ticket(String theater, String dateTime, String jsonSeats, Integer totalPrice) {
        this.theater = theater;
        this.dateTime = dateTime;
        this.jsonSeats = jsonSeats;
        this.totalPrice = totalPrice;
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
