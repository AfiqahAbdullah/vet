package com.example.vet;

public class TreatModel {
    String date, petname, vetname, custIC, treatment, start_time, end_time, medicine, payment;

    TreatModel(){

    }

    public TreatModel(String date, String petname, String vetname, String custIC, String treatment, String start_time, String end_time, String medicine, String payment) {
        this.date = date;
        this.petname = petname;
        this.vetname = vetname;
        this.custIC = custIC;
        this.treatment = treatment;
        this.start_time = start_time;
        this.end_time = end_time;
        this.medicine = medicine;
        this.payment = payment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getVetname() {
        return vetname;
    }

    public void setVetname(String vetname) {
        this.vetname = vetname;
    }

    public String getCustIC() {
        return custIC;
    }

    public void setCustIC(String custIC) {
        this.custIC = custIC;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}