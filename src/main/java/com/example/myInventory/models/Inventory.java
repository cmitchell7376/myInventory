package com.example.myInventory.models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int id;
    private String name;
    private List<Item>items;

    public Inventory(){ }
    public Inventory(String name){
        this.name = name;
        items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }
}
