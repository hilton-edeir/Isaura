package com.isaura.models;

public class Put_Utensil {
    private int id;
    private Utensil utensil;
    private Member member;

    public Put_Utensil(int id, Utensil utensil, Member member) {
        this.id = id;
        this.utensil = utensil;
        this.member = member;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utensil getUtensil() {
        return utensil;
    }

    public void setUtensil(Utensil utensil) {
        this.utensil = utensil;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
