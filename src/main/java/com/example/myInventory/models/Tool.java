package com.example.myInventory.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Tool {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 1, max = 100, message = "field empty")
    private String name;

    @NotNull
    @Size(min = 1, max = 100, message = "field empty")
    private String inUse = "";

    private String foreman;
    private String jobSite;

    @ManyToMany(mappedBy = "tools")
    private List<Equipment>equipmentList;


    public Tool(){}
    public Tool(String name, String foreman, String jobSite){

        this.name = name;
        this.foreman = foreman;
        this.jobSite = jobSite;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInUse() {
        return inUse;
    }

    public String getForeman() {
        return foreman;
    }

    public String getJobSite() {
        return jobSite;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInUse(String inUse) {
        if(inUse.equalsIgnoreCase("yes")){
            inUse = "yes";
        }
        else if(inUse.equalsIgnoreCase("no")){
            inUse = "no";
        }
        inUse = null;
    }

    public void setForeman(String foreman) {
        this.foreman = foreman;
    }

    public void setJobSite(String jobSite) {
        this.jobSite = jobSite;
    }
}
