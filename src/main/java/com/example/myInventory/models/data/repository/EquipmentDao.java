package com.example.myInventory.models.data.repository;

import com.example.myInventory.models.Equipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface EquipmentDao extends CrudRepository<Equipment, Integer> {
    Equipment findOne(int itemId);
}
