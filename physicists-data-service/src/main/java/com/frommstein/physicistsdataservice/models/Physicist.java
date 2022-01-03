package com.frommstein.physicistsdataservice.models;

import java.util.List;

public class Physicist {

    private String physicistId;
    private String physicistName;
    private int yearOfBirth;
    private String almaMater;
    private List<String> listOfWonAwards;


    public Physicist(String physicistId, String physicistName, int yearOfBirth, String almaMater, List<String> listOfWonAwards) {
        this.physicistId = physicistId;
        this.physicistName = physicistName;
        this.yearOfBirth = yearOfBirth;
        this.almaMater = almaMater;
        this.listOfWonAwards = listOfWonAwards;
    }

    public String getPhysicistId() {
        return physicistId;
    }

    public void setPhysicistId(String physicistId) {
        this.physicistId = physicistId;
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
}
