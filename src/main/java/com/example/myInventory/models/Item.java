package com.example.myInventory.models;

public class Item{
    private int id;
    private String name;
    private int qty;
    private int totalQty;
    private String barCode;
    private double price;

    public Item(){ }
    public Item(String name,int totalQty, String barCode, double price){
        this.name = name;
        this.qty = totalQty;
        this.totalQty = totalQty;
        this.barCode = barCode;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public String getBarCode() {
        return barCode;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int subtractQty(int num){
        qty = totalQty - num;
        return qty;
    }

    public int addQty(int num){
        qty = totalQty + num;
        return qty;
    }
}
