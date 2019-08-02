package com.example.myInventory.models;

public class Supplier {

    private int supplierId;
    private static int nextId = 0;

    private String name;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;

    public Supplier(String name, String phoneNumber, String streetAddress, String city, String state, String zip){
        this();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this. city = city;
        this.state = state;
        this.zip = zip;
    }

    public Supplier(){ }

    public int getSupplierId(){
        return supplierId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
