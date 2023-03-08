package com.example.vet;

public class CustomerModel {
    String name,icnum, contact,email,address;

    CustomerModel(){

    }

    public CustomerModel(String name, String icnum, String contact, String email, String address) {
        this.name = name;
        this.icnum = icnum;
        this.contact = contact;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcnum() {
        return icnum;
    }

    public void setIcnum(String icnum) {
        this.icnum = icnum;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
