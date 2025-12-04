package com.example.movieapplication.domain;

public class Seat{
    private String name;
    private Boolean isAvailable;
    private Boolean isSelected;

    public Seat(String name, Boolean isAvailable, Boolean isSelected) {
        this.name = name;
        this.isAvailable = isAvailable;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean b) {
        this.isSelected = b;
    }
}