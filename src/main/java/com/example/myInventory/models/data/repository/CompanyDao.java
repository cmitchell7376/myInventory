package com.example.myInventory.models.data.repository;

import com.example.myInventory.models.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CompanyDao extends CrudRepository<Company, Integer> {
    Company findOne(int storeId);
}
