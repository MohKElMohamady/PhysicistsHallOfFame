package com.frommstein.physicistscatalogueservice.models;

import java.util.List;

public class CatalogueItem {

    private String physicistName;
    private int yearOfBirth;
    private String almaMater;
    private List<String> listOfAwards;


    public CatalogueItem(String physicistName, int yearOfBirth, String almaMater, List<String> listOfAwards) {
        this.physicistName = physicistName;
        this.yearOfBirth = yearOfBirth;
        this.almaMater = almaMater;
        this.listOfAwards = listOfAwards;
    }

    public CatalogueItem() {
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

    public List<String> getListOfAwards() {
        return listOfAwards;
    }

    public void setListOfAwards(List<String> listOfAwards) {
        this.listOfAwards = listOfAwards;
    }
}
