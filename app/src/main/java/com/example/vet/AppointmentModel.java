package com.example.vet;

public class AppointmentModel {
    String petname,custname,custIC,contact, date,time;
    AppointmentModel(){}

    public AppointmentModel(String petname, String custname, String custIC, String contact, String date, String time) {
        this.petname = petname;
        this.custname = custname;
        this.custIC = custIC;
        this.contact = contact;
        this.date = date;
        this.time = time;
    }

    public String getPetname() {return petname;}

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCustIC() {
        return custIC;
    }

    public void setCustIC(String custIC) {
        this.custIC = custIC;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
