package com.isaura.models;

public class Cleaning {
    private int id;
    private String date;
    private String place;

    public Cleaning(int id, String date, String place) {
        this.id = id;
        this.date = date;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
