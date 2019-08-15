package com.example.myInventory.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue
    private int id;

    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
