package com.isaura.models;

public class NotificationUtensil {
    private String id;
    private String date;
    private Member member;
    private Utensil utensil;

    public NotificationUtensil(String id, String date, Member member, Utensil utensil) {
        this.id = id;
        this.date = date;
        this.member = member;
        this.utensil = utensil;
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

    public Utensil getUtensil() {
        return utensil;
    }

    public void setUtensil(Utensil utensil) {
        this.utensil = utensil;
    }
}
