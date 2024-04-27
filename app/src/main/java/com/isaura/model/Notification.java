package com.isaura.model;

public class Notification {
    private String id;
    private String date_notification;
    private String date_done;
    private boolean done;
    private int type;
    private RequestUtensil requestUtensil;
    private Cleaning cleaning;

    public Notification(){}

    public Notification(String id, String date_notification, String date_done, boolean done, int type, RequestUtensil requestUtensil) {
        this.id = id;
        this.date_notification = date_notification;
        this.date_done = date_done;
        this.done = done;
        this.type = type;
        this.requestUtensil = requestUtensil;
    }

    public Notification(String id, String date_notification, String date_done, boolean done, int type, Cleaning cleaning) {
        this.id = id;
        this.date_notification = date_notification;
        this.date_done = date_done;
        this.done = done;
        this.type = type;
        this.cleaning = cleaning;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate_notification() {
        return date_notification;
    }

    public void setDate_notification(String date_notification) {
        this.date_notification = date_notification;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
