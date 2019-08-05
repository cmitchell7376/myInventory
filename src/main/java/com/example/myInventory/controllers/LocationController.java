package com.example.myInventory.controllers;

import com.example.myInventory.models.Item;
import com.example.myInventory.models.Store;
import com.example.myInventory.models.data.StoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("location")
public class LocationController {

    @Autowired
    private StoreDao storeDao;

    @RequestMapping(value = "{name}/store", method = RequestMethod.GET)
    public String index(Model model, @PathVariable String name, @RequestParam int id){

        Store store = storeDao.findOne(id);
        List<Item> items = store.getInventory().getItems();
        List<Item> locationArray = new ArrayList<>();

        for (Item item: items) {
            if (item.getLocation().equalsIgnoreCase(name)){
                locationArray.add(item);
            }
        }

        model.addAttribute("title",name + " Storage");
        model.addAttribute("items",locationArray);

        return "location/index";
    }
}
