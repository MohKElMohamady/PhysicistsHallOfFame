package com.frommstein.physicistscatalogueservice.models;

import java.util.List;

public class PhysicistProfile {

    private String physicistName;
    private int yearOfBirth;
    private String almaMater;
    private List<Prize> listOfAwards;


    public PhysicistProfile(String physicistName, int yearOfBirth, String almaMater, List<Prize> listOfAwards) {
        this.physicistName = physicistName;
        this.yearOfBirth = yearOfBirth;
        this.almaMater = almaMater;
        this.listOfAwards = listOfAwards;
    }

    public PhysicistProfile(Physicist physicist, List<Prize> awards){
        this.physicistName = physicist.getPhysicistName();
        this.yearOfBirth = physicist.getYearOfBirth();
        this.almaMater = physicist.getAlmaMater();
        this.listOfAwards = awards;
    }

    public PhysicistProfile() {
    }

    public String getPhysicistName() {
        return physicistName;
    }

    public void setPhysicistName(String physicistName) {
        this.physicistName = physicistName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getAlmaMater() {
        return almaMater;
    }

    public void setAlmaMater(String almaMater) {
        this.almaMater = almaMater;
    }

    public List<Prize> getListOfAwards() {
        return listOfAwards;
    }

    public void setListOfAwards(List<Prize> listOfAwards) {
        this.listOfAwards = listOfAwards;
    }
}
