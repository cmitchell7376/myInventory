package com.example.myInventory.controllers;

import com.example.myInventory.models.Supplier;
import com.example.myInventory.models.data.SupplierDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private SupplierDao supplierDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title","Supplier's Contact List");
        model.addAttribute("suppliers", supplierDao.findAll());

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

        //check fpr errors
        if(errors.hasErrors()){
            model.addAttribute("title","Add a Supplier");
            return "supplier/add";
        }

        supplierDao.save(supplier);

        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String removeSupplier(Model model){

        model.addAttribute("title","Remove Supplier");
        model.addAttribute("suppliers", supplierDao.findAll());

        return "supplier/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public  String processRemoveSupplier(Model model, @RequestParam int [] supplierIds){

        for (int supplierId : supplierIds){
            supplierDao.delete(supplierDao.findOne(supplierId));
        }
        return "redirect:";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editSupplier(Model model,@PathVariable int id){

        Supplier supplier = supplierDao.findOne(id);

        model.addAttribute("supplier",supplier);
        model.addAttribute("title","Edit " + supplier.getName());
        return "supplier/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEdit(Model model, @RequestParam int id, @ModelAttribute Supplier newSupplier){

        //Edit the previous supplier in the database
        Supplier supplier = supplierDao.findOne(id);
        supplier.setName(newSupplier.getName());
        supplier.setPhoneNumber(newSupplier.getPhoneNumber());
        supplier.setStreetAddress(newSupplier.getStreetAddress());
        supplier.setCity(newSupplier.getCity());
        supplier.setState(newSupplier.getState());
        supplier.setZip(newSupplier.getZip());
        supplierDao.save(supplier);

        return "redirect:";
    }
}
