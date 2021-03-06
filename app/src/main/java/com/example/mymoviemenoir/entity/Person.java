package com.example.mymoviemenoir.entity;

public class Person {
    String firstName;
    String surname;
    String gender;
    String dob;
    String streetAddress;
    String postcode;
    String stateCode;

    public Person(String firstName, String surname, String gender, String dob, String streetAddress, String postcode, String stateCode) {
        this.firstName = firstName;
        this.surname = surname;
        this.gender = gender;
        this.dob = dob;
        this.streetAddress = streetAddress;
        this.postcode = postcode;
        this.stateCode = stateCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}
