package com.example.vet;

public class ReportModel {
    String custIC, date, end_time, medicine, payment, petname, start_time, treatment, vetname;

    ReportModel(){

    }

    public ReportModel(String custIC, String date, String end_time, String medicine, String payment, String petname, String start_time, String treatment, String vetname) {
        this.custIC = custIC;
        this.date = date;
        this.end_time = end_time;
        this.medicine = medicine;
        this.payment = payment;
        this.petname = petname;
        this.start_time = start_time;
        this.treatment = treatment;
        this.vetname = vetname;
    }

    public String getCustIC() {
        return custIC;
    }

    public void setCustIC(String custIC) {
        this.custIC = custIC;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getVetname() {
        return vetname;
    }

    public void setVetname(String vetname) {
        this.vetname = vetname;
    }
}