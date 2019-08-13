package com.example.myInventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Store {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, max = 100, message = "Field empty")
    private String name;

    @NotNull
    @Size(min = 1, max = 100, message = "Field empty")
    private String streetAddress;

    @NotNull
    @Size(min = 1, max = 100, message = "Field empty")
    private String city;

    @NotNull
    @Size(min = 1, max = 100, message = "Field empty")
    private String state;

    @NotNull
    @Size(min = 5, max = 5,message = "Must have 5 digits")
    private String zip;

    private String phoneNumber;

    @ManyToOne
    private Inventory inventory;

    @ManyToOne
    private User user;

    public  Store(){}

    public Store (String name, String streetAddress, String city, String state, String zip,
                  String phoneNumber){
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User getUser() {
        return user;
    }


    public void setName(String name) {

        this.name = name;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
