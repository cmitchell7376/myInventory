package com.example.myInventory.controllers;

import com.example.myInventory.models.Supplier;
import com.example.myInventory.models.data.StoreData;
import com.example.myInventory.models.data.SupplierData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("supplier")
public class SupplierController {

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title","Supplier's Contact List");
        model.addAttribute("suppliers", SupplierData.getAll());

        return "supplier/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addSupplierForm(Model model){

        model.addAttribute("title","Add a Supplier");
        model.addAttribute(new Supplier());

        return "supplier/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddSupplierForm(@ModelAttribute @Valid Supplier supplier, Errors errors, Model model){

        if(errors.hasErrors()){
            model.addAttribute("title","Add a Supplier");
            return "store/add";
        }

        SupplierData.add(supplier);

        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String removeSupplier(Model model){

        model.addAttribute("title","Remove Supplier");
        model.addAttribute("suppliers", SupplierData.getAll());

        return "supplier/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public  String processRemoveSupplier(Model model, @RequestParam int [] supplierIds){

        for (int supplierId : supplierIds){
            SupplierData.remove(supplierId);
        }
        return "redirect:";
    }
}
