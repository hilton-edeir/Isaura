package com.isaura.model;

public class NotificationCleaning {
    private String id;
    private String date;
    private Member member;
    private Place place;

    public NotificationCleaning(String id, String date, Member member, Place place) {
        this.id = id;
        this.date = date;
        this.member = member;
        this.place = place;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
