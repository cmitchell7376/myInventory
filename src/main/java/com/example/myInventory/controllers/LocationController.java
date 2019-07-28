package com.example.myInventory.controllers;

import com.example.myInventory.models.Item;
import com.example.myInventory.models.Store;
import com.example.myInventory.models.data.StoreData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("location")
public class LocationController {

    @RequestMapping(value = "{name}/store", method = RequestMethod.GET)
    public String index(Model model, @PathVariable String name, @RequestParam int id){
        Store store = StoreData.getById(id);
        ArrayList<Item> items = store.getInventory().getItems();
        ArrayList<Item> locationArray = new ArrayList<>();
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
