package com.isaura.model;

import java.util.List;

public class CleaningDistribution {

    private String date_of_cleaning;
    private List<Place> list_places;
    private List<String> member_distribution;

    public CleaningDistribution(){}

    public CleaningDistribution(String date_of_cleaning, List<Place> place, List<String> member_distribution) {
        this.date_of_cleaning = date_of_cleaning;
        this.list_places = place;
        this.member_distribution = member_distribution;
    }

    public String getDate_of_cleaning() {
        return date_of_cleaning;
    }

    public void setDate_of_cleaning(String date_of_cleaning) {
        this.date_of_cleaning = date_of_cleaning;
    }

    public List<Place> getList_places() {
        return list_places;
    }

    public void setList_places(List<Place> list_places) {
        this.list_places = list_places;
    }

    public List<String> getMember_distribution() {
        return member_distribution;
    }

    public void setMember_distribution(List<String> member_distribution) {
        this.member_distribution = member_distribution;
    }
}
