package com.example.myInventory.models;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name = "firstName")
    @NotEmpty(message = "*Please provide your name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    private String email;

    @Column(name = "username")
    @NotEmpty(message = "Please provide Username")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(name = "active")
    private int active;

    @ManyToMany
    private List<Supplier> suppliers;

    @ManyToMany
    private List<Store> stores;

    @ManyToMany
    private List<EquipmentStore> equipmentStores;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User (){ }

    public User(String firstName, String lastName, String email, String username, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getActive() {
        return active;
    }

    public List<Store> getStores() {
        return stores;
    }

    public List<EquipmentStore> getEquipmentStores() {
        return equipmentStores;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public Set<Role> getRoles() {
        return roles;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public void setEquipmentStores(List<EquipmentStore> equipmentStores) {
        this.equipmentStores = equipmentStores;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void  removeStore (Store item){
        stores.remove(item);
    }

    public void  removeEquipmentStore (EquipmentStore item){
        equipmentStores.remove(item);
    }
}
