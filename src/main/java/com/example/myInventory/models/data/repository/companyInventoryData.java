package com.example.myInventory.models.data.repository;

import com.example.myInventory.models.Company;
import com.example.myInventory.models.Equipment;

import java.util.List;

public class companyInventoryData {

    private static CompanyDao companyDao;

    //check item by name
    public static Equipment checkByName(Company company, int equipmentId){

        List<Equipment> items = company.getInventory().getEquipment();

        for (Equipment item: items) {
            if (item.getId() == equipmentId){
                return item;
            }
        }
        return null;
    }
}

