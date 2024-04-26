package com.isaura.model;

public class Member {
    private String name;
    private String email;
    private String code;
    private String url_image;

    public Member(){}

    public Member(String name, String email, String code, String url_image) {
        this.name = name;
        this.email = email;
        this.code = code;
        this.url_image = url_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}
