package com.example.myInventory.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String email;
    private String userName;
    private String password;
    private List<Store> stores = new ArrayList<>();

    public User (){ }

    public User(String email, String userName,String password){
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
