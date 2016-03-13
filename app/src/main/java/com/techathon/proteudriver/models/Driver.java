package com.techathon.proteudriver.models;

/**
 * Created by Proteu on 12/03/16.
 */
public class Driver {
    private int id;
    private String email;
    private String name;
    private String cc;
    private String boodType;
    private String mobilePhone;
    private String emergencyContact;
    private String notes;
    private String diseases;

    public Driver(String email, String name, String cc, String boodType, String mobilePhone, String emergencyContact, String notes, String diseases) {
        this.email = email;
        this.name = name;
        this.cc = cc;
        this.boodType = boodType;
        this.mobilePhone = mobilePhone;
        this.emergencyContact = emergencyContact;
        this.notes = notes;
        this.diseases = diseases;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBoodType() {
        return boodType;
    }

    public void setBoodType(String boodType) {
        this.boodType = boodType;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }
}
