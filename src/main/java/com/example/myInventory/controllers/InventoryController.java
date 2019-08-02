package com.example.myInventory.controllers;

import com.example.myInventory.models.Item;
import com.example.myInventory.models.Store;
import com.example.myInventory.models.data.InventoryData;
import com.example.myInventory.models.data.ItemData;
import com.example.myInventory.models.data.StoreData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("inventory")
public class InventoryController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, @RequestParam int id){

        Store store = StoreData.getById(id);
        model.addAttribute("items",store.getInventory().getItems());
        model.addAttribute("store",store);
        model.addAttribute("title",store.getInventory().getName()+" Inventory");

        return "inventory/index";
    }

    @RequestMapping(value = "add/{id}", method = RequestMethod.GET)
    public String addItemForm(Model model,@PathVariable int id){

        model.addAttribute(new Item());
        model.addAttribute("storeId",id);
        model.addAttribute("title","Add item");

        return "inventory/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddItemForm(Model model,@ModelAttribute @Valid Item item,@RequestParam int storeId){

        Store store = StoreData.getById(storeId);
        store.getInventory().addItem(item);

        return "redirect:?id=" + storeId;
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public String removeItemForm(Model model,@PathVariable int id){

        model.addAttribute("title","Remove Stores");
        model.addAttribute("items",StoreData.getById(id).getInventory().getItems());
        model.addAttribute("storeId",id);

        return "inventory/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public  String processRemoveStore(Model model, @RequestParam int [] itemIds, @RequestParam int storeId) {

        for (int itemId : itemIds) {
            Item item = InventoryData.checkByName(storeId,itemId);
            StoreData.getById(storeId).getInventory().removeItem(item);
        }
        return "redirect:?id=" + storeId;
    }

    @RequestMapping(value = "edit/{itemId}/store", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable int itemId,@RequestParam int id){
        model.addAttribute("storeId",id);
        Item newItem = ItemData.getItem(itemId,id);
        model.addAttribute("item",newItem);
        model.addAttribute("title","Edit " + newItem.getName());
        return "inventory/edit";
    }

    @RequestMapping(value ="edit", method = RequestMethod.POST)
    public String processEdit(Model model, @RequestParam int storeId, @RequestParam int itemId,
                              @ModelAttribute Item newItem){
        Item updateItem = ItemData.getItem(itemId,storeId);
        updateItem.setName(newItem.getName());
        updateItem.setBarCode(newItem.getBarCode());
        updateItem.setTotalQty(newItem.getTotalQty());
        updateItem.setAvailable(newItem.getAvailable());
        updateItem.setLocation(newItem.getLocation());
        updateItem.setPrice(newItem.getPrice());
        return "redirect:?id=" + storeId;
    }
}
