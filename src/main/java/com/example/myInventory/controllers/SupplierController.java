package com.example.myInventory.controllers;

import com.example.myInventory.models.Supplier;
import com.example.myInventory.models.User;
import com.example.myInventory.models.data.repository.SupplierDao;
import com.example.myInventory.models.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/{userId}")
    public String index(Model model,@PathVariable int userId){

        List<Supplier> suppliers = userRepository.findOne(userId).getSuppliers();

        model.addAttribute("title","Supplier's Contact List");
        model.addAttribute("suppliers", suppliers);

        return "supplier/index";
    }

    @RequestMapping(value = "add/user/{userId}", method = RequestMethod.GET)
    public String addSupplierForm(Model model,@PathVariable int userId){

        model.addAttribute("title","Add a Supplier");
        model.addAttribute("userId",userId);
        model.addAttribute(new Supplier());

        return "supplier/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddSupplierForm(@ModelAttribute @Valid Supplier supplier, Errors errors, Model model,
                                         @RequestParam int userId){

        User user = userRepository.findOne(userId);

        //check fpr errors
        if(errors.hasErrors()){
            model.addAttribute("title","Add a Supplier");
            return "supplier/add";
        }

        user.getSuppliers().add(supplier);
        supplierDao.save(supplier);

        return "redirect:user/" + userId;
    }

    @RequestMapping(value = "user/{userId}/remove", method = RequestMethod.GET)
    public String removeSupplier(Model model, @PathVariable int userId){

        model.addAttribute("title","Remove Supplier");
        model.addAttribute("userId",userId);
        model.addAttribute("suppliers", userRepository.findOne(userId).getSuppliers());

        return "supplier/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public  String processRemoveSupplier(Model model, @RequestParam(value = "supplierIds", required = false) int [] supplierIds,
                                         @RequestParam int userId){
        User user = userRepository.findOne(userId);

        if(supplierIds  == null){
            model.addAttribute("title","Remove Supplier");
            model.addAttribute("userId",userId);
            model.addAttribute("suppliers", userRepository.findOne(userId).getSuppliers());
            model.addAttribute("error","please check one of the boxes");
            return "supplier/remove";
        }

        for (int supplierId : supplierIds){
            Supplier supplier = supplierDao.findOne(supplierId);
            user.getSuppliers().remove(supplier);
            supplierDao.delete(supplierId);
        }

        return "redirect:user/" + userId;
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
