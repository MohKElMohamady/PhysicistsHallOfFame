package com.frommstein.prizeinfoservice.models;

import java.util.HashMap;
import java.util.Map;

public class Prize {

    private String prizeId;
    private String prizeName;
    private int firstAwarded;
    private int monetaryAward;
    private String shortDescription;
    private String wikipediaLink;
    private Map<String, Integer> laureteAndHisTimeOfAwarding;


    public Prize(String prizeId, String prizeName, int firstAwarded, int monetaryAward, String shortDescription, String wikipediaLink, Map<String, Integer> laureteAndHisTimeOfAwarding) {
        this.prizeId = prizeId;
        this.prizeName = prizeName;
        this.firstAwarded = firstAwarded;
        this.monetaryAward = monetaryAward;
        this.shortDescription = shortDescription;
        this.wikipediaLink = wikipediaLink;
        this.laureteAndHisTimeOfAwarding = new HashMap<>();
        this.laureteAndHisTimeOfAwarding.put("82",1954);
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public int getFirstAwarded() {
        return firstAwarded;
    }

    public void setFirstAwarded(int firstAwarded) {
        this.firstAwarded = firstAwarded;
    }

    public int getMonetaryAward() {
        return monetaryAward;
    }

    public void setMonetaryAward(int monetaryAward) {
        this.monetaryAward = monetaryAward;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getWikipediaLink() {
        return wikipediaLink;
    }

    public void setWikipediaLink(String wikipediaLink) {
        this.wikipediaLink = wikipediaLink;
    }

    public String getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(String prizeId) {
        this.prizeId = prizeId;
    }

    public Map<String, Integer> getLaureteAndHisTimeOfAwarding() {
        return laureteAndHisTimeOfAwarding;
    }

    public void setLaureteAndHisTimeOfAwarding(Map<String, Integer> laureteAndHisTimeOfAwarding) {
        this.laureteAndHisTimeOfAwarding = laureteAndHisTimeOfAwarding;
    }
}
