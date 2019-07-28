package com.example.myInventory.models;

public class Supplier {

    private int supplierId;
    private static int nextId = 0;

    private String name;
    private String type;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private Inventory inventory;

    public Supplier(String name,String type,String streetAddress,String city,String state,String zip){
        this();
        this.name = name;
        this.type = type;
        this.streetAddress = streetAddress;
        this. city = city;
        this.state = state;
        this.zip = zip;
    }

    public Supplier(){ }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
