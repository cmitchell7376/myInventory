package com.example.myInventory.models;

public class Item{
    private int id;
    private String name;
    private String itemNumber;
    private int totalQty;
    private int available;
    private int outGoing;
    private double price;

    public Item(){ }
    public Item(String name, String itemNumber, int totalQty, double price){
        this.name = name;
        this.itemNumber = itemNumber;
        this.totalQty = totalQty;
        available = 0;
        outGoing = 0;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public int getAvailable() {
        return available;
    }

    public int getOutGoing() {
        return outGoing;
    }

    public double getPrice() {
        return price;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setOutGoing(int outGoing) {
        this.outGoing = outGoing;
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
