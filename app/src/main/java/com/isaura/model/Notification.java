package com.isaura.model;

public class Notification {
    private String date;
    private boolean done;
    private int type;
    private Member member;
    private Utensil utensil;

    public Notification(){}

    public Notification(String date, boolean done, int type, Member member, Utensil utensil) {
        this.date = date;
        this.done = done;
        this.type = type;
        this.member = member;
        this.utensil = utensil;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
