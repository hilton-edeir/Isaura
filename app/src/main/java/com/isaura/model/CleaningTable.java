package com.isaura.model;

import java.util.List;

public class CleaningTable {

    private Place place;
    private List<String> member_distribution;

    public CleaningTable(){}

    public CleaningTable(Place place, List<String> member_distribution) {
        this.place = place;
        this.member_distribution = member_distribution;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<String> getMember_distribution() {
        return member_distribution;
    }

    public void setMember_distribution(List<String> member_distribution) {
        this.member_distribution = member_distribution;
    }
}
