package com.example.myInventory.controllers;

import com.example.myInventory.models.Inventory;
import com.example.myInventory.models.Store;
import com.example.myInventory.models.data.StoreData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("store")
public class HomeController {

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("title","Username Stores");
        model.addAttribute("stores", StoreData.getAll());
        return "store/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addStoreForm(Model model){

        model.addAttribute("title","Add a Store");
        model.addAttribute(new Store());
        return "store/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddStoreForm(@ModelAttribute @Valid Store store, Errors errors, Model model,
                                      @RequestParam String inventoryName){

        if(errors.hasErrors()){
            model.addAttribute("title","Add a Store");
            return "store/add";
        }

        Inventory inventory = new Inventory(inventoryName);
        store.setInventory(inventory);
        StoreData.add(store);

        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String removeStore(Model model){

        model.addAttribute("title","Remove Stores");
        model.addAttribute("stores",StoreData.getAll());
        return "store/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public  String processRemoveStore(Model model, @RequestParam int [] storeIds){

        for (int storeId : storeIds){
            StoreData.remove(storeId);
        }
        return "redirect:";
    }
}
