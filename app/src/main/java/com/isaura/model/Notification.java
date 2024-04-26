package com.isaura.model;

public class Notification {
    private String date;
    private boolean done;
    private String member_name;
    private String utensil_name;

    public Notification() {}

    public Notification( String utensil_name, String date, boolean done, String member_name) {
        this.utensil_name = utensil_name;
        this.date = date;
        this.done = done;
        this.member_name = member_name;
    }

    public String getUtensil_name() {
        return utensil_name;
    }

    public void setUtensil_name(String utensil_name) {
        this.utensil_name = utensil_name;
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

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

}
