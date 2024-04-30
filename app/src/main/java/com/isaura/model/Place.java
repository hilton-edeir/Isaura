package com.isaura.model;

import java.util.List;

public class Place {
    private String name;
    private String url_image;
    private List<String> cleaning_order_list;
    private List<String> last_cleaning_order_list;
    private List<String> next_cleaning_order_list;

    public Place() {}

    public Place(String name, String url_image, List<String> cleaning_order_list, List<String> last_cleaning_order_list, List<String> next_cleaning_order_list) {
        this.name = name;
        this.url_image = url_image;
        this.cleaning_order_list = cleaning_order_list;
        this.last_cleaning_order_list = last_cleaning_order_list;
        this.next_cleaning_order_list = next_cleaning_order_list;
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

    public List<String> getCleaning_order_list() {
        return cleaning_order_list;
    }

    public void setCleaning_order_list(List<String> cleaning_order_list) {
        this.cleaning_order_list = cleaning_order_list;
    }

    public List<String> getLast_cleaning_order_list() {
        return last_cleaning_order_list;
    }

    public void setLast_cleaning_order_list(List<String> last_cleaning_order_list) {
        this.last_cleaning_order_list = last_cleaning_order_list;
    }

    public List<String> getNext_cleaning_order_list() {
        return next_cleaning_order_list;
    }

    public void setNext_cleaning_order_list(List<String> next_cleaning_order_list) {
        this.next_cleaning_order_list = next_cleaning_order_list;
    }
}
