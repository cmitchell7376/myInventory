package com.example.myInventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class CompanyInventory {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @OneToMany
    @JoinColumn(name = "inventory_id")
    private List<Company> companies;

    @ManyToMany
    private List<Equipment>equipment;


    public CompanyInventory() { }

    public CompanyInventory(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEquipment(Equipment equip){
        equipment.add(equip);
    }

    public void removeEquipment(Equipment equip){
        equipment.remove(equip);
    }
}
