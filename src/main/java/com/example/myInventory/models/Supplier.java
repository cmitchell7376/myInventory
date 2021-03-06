package com.example.myInventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Supplier {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, max = 100,message = "field empty")
    private String name;

    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;

    @ManyToMany(mappedBy = "suppliers")
    private List<User> users;

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

    public int getId(){
        return id;
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

    public List<User> getUsers() {
        return users;
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

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
