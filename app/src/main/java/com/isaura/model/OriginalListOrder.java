package com.isaura.model;

public class OriginalListOrder {
    private int order;
    private String member;

    public OriginalListOrder() {}

    public OriginalListOrder(int order, String member) {
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
