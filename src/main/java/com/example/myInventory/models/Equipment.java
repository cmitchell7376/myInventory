package com.example.myInventory.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Equipment {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, max = 100, message = "field empty")
    private String name;

    private int available;

    private int quantity;

    @ManyToMany
    private List<Tool> tools;

    @ManyToMany(mappedBy = "equipment")
    private List<CompanyInventory>companyInventories;

    public Equipment(){}
    public Equipment(String name){
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
        for(int i = 0; i < tools.size(); ++i){
            if(tools.get(i).getInUse().equalsIgnoreCase("no")){
                notInUse += 1;
            }
            available = notInUse;
        }
        return available;
    }

    public int getQuantity() {
        quantity = tools.size();
        return quantity;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void addTool(Tool tool){
        tools.add(tool);
    }

    public void removeTool(Tool tool){
        tools.remove(tool);
    }
}
