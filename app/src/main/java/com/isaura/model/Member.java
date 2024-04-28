package com.isaura.model;

public class Member {
    private String name;
    private String email;
    private String code;
    private String url_image;
    private int assigned_table_order;

    public Member(){}

    public Member(String name, String email, String code, String url_image, int assigned_table_order) {
        this.name = name;
        this.email = email;
        this.code = code;
        this.url_image = url_image;
        this.assigned_table_order = assigned_table_order;
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

    public int getAssigned_table_order() {
        return assigned_table_order;
    }

    public void setAssigned_table_order(int assigned_table_order) {
        this.assigned_table_order = assigned_table_order;
    }
}
