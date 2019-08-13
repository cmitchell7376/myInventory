package com.example.myInventory.models;

import java.util.List;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private List<Store> stores;

    public User (){ }

    public User(String firstName, String lastName, String email, String username, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Store> getStores() {
        return stores;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
