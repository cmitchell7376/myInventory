package com.example.myInventory.models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int id;
    private String name;
    private ArrayList<Item>items = new ArrayList<>();

    public Inventory(){ }
    public Inventory(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }
}
