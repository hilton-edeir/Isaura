package com.isaura.model;

import java.util.List;

public class LastRotatedList {
    private int order;
    private String member;

    public LastRotatedList() {}

    public LastRotatedList(int order, String member) {
        this.order = order;
        this.member = member;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}
