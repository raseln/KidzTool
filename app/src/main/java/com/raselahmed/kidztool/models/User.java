package com.raselahmed.kidztool.models;

public class User {

    private String  uid;
    private String name, email, institution;

    public User(String uid, String name, String email, /*String password,*/ String institution) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        //this.password = password;
        this.institution = institution;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

//    public String getPassword() {
//        return password;
//    }

    public String getInstitution() {
        return institution;
    }
}
