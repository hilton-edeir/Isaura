package com.isaura.model;

public class Activity {
    private String id;
    private int type;
    private String date_created;
    private boolean done;
    private String date_done;
    private String member;
    private String place;
    private Utensil utensil;

    public Activity() {}

    public Activity(String id, int type, String date_created, boolean done, String date_done, String member, String place) {
        this.id = id;
        this.type = type;
        this.date_created = date_created;
        this.done = done;
        this.date_done = date_done;
        this.member = member;
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDate_done() {
        return date_done;
    }

    public void setDate_done(String date_done) {
        this.date_done = date_done;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Utensil getUtensil() {
        return utensil;
    }

    public void setUtensil(Utensil utensil) {
        this.utensil = utensil;
    }
}
