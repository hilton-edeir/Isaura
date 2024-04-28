package com.isaura.model;

public class Activity {
    private int id;
    private int type;
    private String date_created;
    private String date_done;
    private boolean done;
    private Member member;
    private Place place;
    private Utensil utensil;

    public Activity(){}

    public Activity(int id, String date_created, String date_done, boolean done, int type, Member member, Utensil utensil) {
        this.id = id;
        this.date_created = date_created;
        this.date_done = date_done;
        this.done = done;
        this.type = type;
        this.member = member;
        this.utensil = utensil;
    }

    public Activity(int id, String date_created, String date_done, boolean done, int type, Member member, Place place) {
        this.id = id;
        this.date_created = date_created;
        this.date_done = date_done;
        this.done = done;
        this.type = type;
        this.member = member;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_done() {
        return date_done;
    }

    public void setDate_done(String date_done) {
        this.date_done = date_done;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
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

    public Utensil getUtensil() {
        return utensil;
    }

    public void setUtensil(Utensil utensil) {
        this.utensil = utensil;
    }
}
