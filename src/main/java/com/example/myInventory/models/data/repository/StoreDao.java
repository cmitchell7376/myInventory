package com.example.myInventory.models.data.repository;

import com.example.myInventory.models.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StoreDao extends CrudRepository<Store, Integer> {
    Store findOne(int id);
}
