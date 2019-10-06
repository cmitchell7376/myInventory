package com.example.myInventory.models.data.repository;

import com.example.myInventory.models.CompanyInventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CompanyInventoryDao extends CrudRepository<CompanyInventory, Integer> {
}
