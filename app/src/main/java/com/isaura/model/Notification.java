package com.isaura.model;

public class Notification {
    private String date;
    private boolean done;
    private RequestUtensil requestUtensil;
    private Cleaning cleaning;

    public Notification(){}

    public Notification(String date, boolean done, RequestUtensil requestUtensil) {
        this.date = date;
        this.done = done;
        this.requestUtensil = requestUtensil;
    }

    public Notification(String date, boolean done, Cleaning cleaning) {
        this.date = date;
        this.done = done;
        this.cleaning = cleaning;
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

    public RequestUtensil getRequestUtensil() {
        return requestUtensil;
    }

    public void setRequestUtensil(RequestUtensil requestUtensil) {
        this.requestUtensil = requestUtensil;
    }

    public Cleaning getCleaning() {
        return cleaning;
    }

    public void setCleaning(Cleaning cleaning) {
        this.cleaning = cleaning;
    }
}
