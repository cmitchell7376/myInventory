package com.example.myInventory.models.data.repository;

import com.example.myInventory.models.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface InventoryDao extends CrudRepository<Inventory, Integer> {
}
