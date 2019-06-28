package com.example.myInventory.models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int id;
    private String name;
    private List<Item>items;

    public Inventory(){ }
    public Inventory(String name){
        items = new ArrayList<>();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }
}
