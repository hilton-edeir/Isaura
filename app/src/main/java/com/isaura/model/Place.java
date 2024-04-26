package com.isaura.model;

public class Place {
    private String name;
    private String url_image;

    public Place() {}

    public Place(String name, String url_image) {
        this.name = name;
        this.url_image = url_image;
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
}
