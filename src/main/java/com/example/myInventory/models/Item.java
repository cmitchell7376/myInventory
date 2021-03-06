package com.example.myInventory.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Item{

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, max = 100, message = "field empty")
    private String name;

    private String barCode;

    @NotNull
    @Min(value = 0,message = "field empty")
    private int totalQty;

    private int available;
    private String location;
    private double price;

    @ManyToMany(mappedBy = "items")
    private List<Inventory> inventories;

    public Item(){ }

    public Item(String name, String barCode, int totalQty, String location, double price){
        this.name = name;
        this.barCode = barCode;
        this.totalQty = totalQty;
        available = totalQty;
        this.location = location;
        this.price = price;
    }

    public Item(String name, double price){
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBarCode() {
        return barCode;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public int getAvailable() {
        return available;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int purchaseItem(int num){
        available = totalQty - num;
        return available;
    }

    public int orderItem(int num){
        available = totalQty + num;
        return available;
    }
}
