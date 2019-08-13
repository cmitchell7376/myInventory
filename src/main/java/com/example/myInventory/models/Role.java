package com.example.myInventory.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue
    private int id;

    private String role;
}
