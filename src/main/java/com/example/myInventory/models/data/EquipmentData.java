package com.example.myInventory.models.data;

import com.example.myInventory.models.Company;
import com.example.myInventory.models.Equipment;
import com.example.myInventory.models.data.repository.CompanyDao;

import java.util.List;

public class EquipmentData {


    private static CompanyDao companyDao;

    public static Equipment getEquipment(int id, Company company){
        List<Equipment> items = company.getCompanyInventory().getEquipment();
        Equipment newEquipment = new Equipment();
        for (Equipment item: items) {
            if(item.getId() == id){
                newEquipment = item;
            }
        }
        return newEquipment;
    }
}
