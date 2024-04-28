package com.isaura.model;

public class Place {
    private String name;
    private String url_image;
    private int assigned_order;

    public Place() {}

    public Place(String name, String url_image) {
        this.name = name;
        this.url_image = url_image;
    }

    public Place(String name, String url_image, int assigned_order) {
        this.name = name;
        this.url_image = url_image;
        this.assigned_order = assigned_order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public int getAssigned_order() {
        return assigned_order;
    }

    public void setAssigned_order(int assigned_order) {
        this.assigned_order = assigned_order;
    }
}
