package com.raselahmed.kidztool.models;



public class BioDict {

    private String generalName, scientificName;

    public BioDict(String generalName, String scientificName) {
        this.generalName = generalName;
        this.scientificName = scientificName;
    }

    public String getGeneralName() {
        return generalName;
    }

    public String getScientificName() {
        return scientificName;
    }
}
