package com.raselahmed.kidztool;



class BioDict {
    String generalName, scientificName;

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
