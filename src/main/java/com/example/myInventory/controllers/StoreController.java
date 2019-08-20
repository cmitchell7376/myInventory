package com.example.myInventory.controllers;

import com.example.myInventory.models.Inventory;
import com.example.myInventory.models.Store;
import com.example.myInventory.models.data.repository.InventoryDao;
import com.example.myInventory.models.data.repository.StoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("store")
public class StoreController {

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private InventoryDao inventoryDao;

    @RequestMapping(value = "/{user}")
    public String index(Model model, @PathVariable int user){

        model.addAttribute("title","Username Stores");
        model.addAttribute("stores", storeDao.findAll());

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

        //checks for errors
        if(errors.hasErrors()){
            model.addAttribute("title","Add a Store");
            return "store/add";
        }

        //create store and it's inventory
        Inventory inventory = new Inventory(inventoryName);
        inventoryDao.save(inventory);
        store.setInventory(inventory);
        storeDao.save(store);

        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String removeStore(Model model){

        model.addAttribute("title","Remove Stores");
        model.addAttribute("stores",storeDao.findAll());

        return "store/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public  String processRemoveStore(Model model, @RequestParam int [] storeIds){

        for (int storeId : storeIds){
            storeDao.delete(storeId);
        }
        return "redirect:";
    }
}
