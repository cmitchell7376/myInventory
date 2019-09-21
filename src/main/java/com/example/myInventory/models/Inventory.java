package com.example.myInventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Inventory {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @OneToMany
    @JoinColumn(name = "inventory_id")
    private List<Store>stores;

    @ManyToMany
    private List<Item>items;

    @ManyToMany
    private List<EquipmentGroup> equipmentGroups;

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

    public List<Item> getItems() {
        return items;
    }

    public List<EquipmentGroup> getEquipmentGroups() {
        return equipmentGroups;
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
