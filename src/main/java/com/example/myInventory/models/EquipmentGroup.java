package com.example.myInventory.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class EquipmentGroup {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, max = 100, message = "field empty")
    private String name;

    private int available;
    private int quantity;

    @ManyToMany
    private List<Equipment> equipment;

    @ManyToMany(mappedBy = "equipmentGroups")
    private List<Inventory> inventories;

    public EquipmentGroup(){}
    public EquipmentGroup(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAvailable() {
        int notInUse = 0;
        for(int i = 0; i < equipment.size(); ++i){
            if(equipment.get(i).getInUse().equalsIgnoreCase("no")){
                notInUse += 1;
            }
        }
        available = notInUse;
        return available;
    }

    public int getQuantity() {
        quantity = equipment.size();
        return quantity;
    }

    public List<Equipment> getEquipment() {
        return equipment;
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
