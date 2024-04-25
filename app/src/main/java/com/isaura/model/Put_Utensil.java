package com.isaura.model;

public class Put_Utensil {
    private String id;
    private String date;
    private Utensil utensil;
    private Member member;

    public Put_Utensil(String id, String date, Utensil utensil, Member member) {
        this.id = id;
        this.date = date;
        this.utensil = utensil;
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

    public Utensil getUtensil() {
        return utensil;
    }

    public void setUtensil(Utensil utensil) {
        this.utensil = utensil;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
