package com.isaura.model;

public class AllActivities {
    private String id;
    private Cleaning cleaning;
    private Put_Utensil putUtensil;

    public AllActivities(String id, Cleaning cleaning, Put_Utensil putUtensil) {
        this.id = id;
        this.cleaning = cleaning;
        this.putUtensil = putUtensil;
    }

    public AllActivities(String id, Cleaning cleaning) {
        this.id = id;
        this.cleaning = cleaning;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cleaning getCleaning() {
        return cleaning;
    }

    public void setCleaning(Cleaning cleaning) {
        this.cleaning = cleaning;
    }

    public Put_Utensil getPutUtensil() {
        return putUtensil;
    }

    public void setPutUtensil(Put_Utensil putUtensil) {
        this.putUtensil = putUtensil;
    }
}