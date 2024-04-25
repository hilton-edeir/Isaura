package com.isaura.model;

public class Cleaning {
    private String id;
    private String date;
    private Place place;
    private Member member;

    public Cleaning(String id, String date, Place place, Member member) {
        this.id = id;
        this.date = date;
        this.place = place;
        this.member = member;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
