package com.example.myInventory.models.data;

import com.example.myInventory.models.Company;
import com.example.myInventory.models.Equipment;
import com.example.myInventory.models.data.repository.CompanyDao;

import java.util.List;

public class CompanyInventoryData {

    private static CompanyDao companyDao;

    //check item by name
    public static Equipment checkByName(Company company, int equipmentId){

        List<Equipment> items = company.getCompanyInventory().getEquipment();

        for (Equipment item: items) {
            if (item.getId() == equipmentId){
                return item;
            }
        }
        return null;
    }
}

