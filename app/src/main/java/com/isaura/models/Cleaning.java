package com.isaura.models;

public class Cleaning {
    private int id;
    private String date;
    private String place;
    private Member member;

    public Cleaning(int id, String date, String place, Member member) {
        this.id = id;
        this.date = date;
        this.place = place;
        this.member = member;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
