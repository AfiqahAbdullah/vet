package com.example.vet;

public class PetModel {
    String petName, petType, petSex, petBreed, petDOB, petSurl, petOwnerIC;

    PetModel(){

    }

    public PetModel(String petName, String petType, String petSex, String petBreed, String petDOB, String petSurl, String petOwnerIC) {
        this.petName = petName;
        this.petType = petType;
        this.petSex = petSex;
        this.petBreed = petBreed;
        this.petDOB = petDOB;
        this.petSurl = petSurl;
        this.petOwnerIC = petOwnerIC;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetSex() {
        return petSex;
    }

    public void setPetSex(String petSex) {
        this.petSex = petSex;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetDOB() {
        return petDOB;
    }

    public void setPetDOB(String petDOB) {
        this.petDOB = petDOB;
    }

    public String getPetSurl() {
        return petSurl;
    }

    public void setPetSurl(String petSurl) {
        this.petSurl = petSurl;
    }

    public String getPetOwnerIC() {
        return petOwnerIC;
    }

    public void setPetOwnerIC(String petOwnerIC) {
        this.petOwnerIC = petOwnerIC;
    }
}
