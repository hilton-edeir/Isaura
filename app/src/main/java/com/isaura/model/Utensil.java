package com.isaura.model;

public class Utensil {
    private String name;
    private String url_image;
    private boolean stock;

    public Utensil() {}

    public Utensil(String name, String url_image, boolean stock) {
        this.name = name;
        this.url_image = url_image;
        this.stock = stock;
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

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }
}
