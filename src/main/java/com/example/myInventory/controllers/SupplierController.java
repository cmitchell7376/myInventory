package com.example.myInventory.controllers;

import com.example.myInventory.models.data.StoreData;
import com.example.myInventory.models.data.SupplierData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("supplier")
public class SupplierController {

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title","Suppliers");
        model.addAttribute("suppliers", SupplierData.getAll());

        return "supplier/index";
    }
}
