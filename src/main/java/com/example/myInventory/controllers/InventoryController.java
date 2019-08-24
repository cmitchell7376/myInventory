package com.example.myInventory.controllers;

import com.example.myInventory.models.Inventory;
import com.example.myInventory.models.Item;
import com.example.myInventory.models.Store;
import com.example.myInventory.models.data.*;
import com.example.myInventory.models.data.repository.InventoryDao;
import com.example.myInventory.models.data.repository.ItemDao;
import com.example.myInventory.models.data.repository.StoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private InventoryDao inventoryDao;

    @Autowired
    private ItemDao itemDao;

    @RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
    public String index(Model model, @RequestParam int id, @PathVariable int userId){

        Store store = storeDao.findOne(id);
        model.addAttribute("items",store.getInventory().getItems());
        model.addAttribute("store",store);
        model.addAttribute("userId",userId);
        model.addAttribute("title",store.getInventory().getName()+" Inventory");

        return "inventory/index";
    }

    @RequestMapping(value = "/user/{userId}/add/{id}", method = RequestMethod.GET)
    public String addItemForm(Model model,@PathVariable int id, @PathVariable int userId){

        model.addAttribute(new Item());
        model.addAttribute("storeId",id);
        model.addAttribute("userId",userId);
        model.addAttribute("title","Add item");

        return "inventory/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddItemForm(Model model, @ModelAttribute @Valid Item item, Errors errors,@RequestParam int storeId,
                                     @RequestParam int userId){

        //checks for errors
        if(errors.hasErrors()){
            model.addAttribute("storeId",storeId);
            model.addAttribute("title","Add item");

            return "inventory/add";
        }

        //finds the store and it's inventory then adds new item to inventory
        Store store = storeDao.findOne(storeId);
        int inventoryId = store.getInventory().getId();
        Inventory inventory = inventoryDao.findOne(inventoryId);
        itemDao.save(item);
        inventory.addItem(item);
        inventoryDao.save(inventory);

        return "redirect:user/" + userId + "?id=" + storeId;
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public String removeItemForm(Model model,@PathVariable int id){

        model.addAttribute("title","Remove Stores");
        model.addAttribute("items",storeDao.findOne(id).getInventory().getItems());
        model.addAttribute("storeId",id);

        return "inventory/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public  String processRemoveStore(Model model, @RequestParam int [] itemIds, @RequestParam int storeId) {

        Store store = storeDao.findOne(storeId);

        for (int itemId : itemIds) {
            Item item = InventoryData.checkByName(store,itemId);
            storeDao.findOne(storeId).getInventory().removeItem(item);
            itemDao.delete(item);
        }

        return "redirect:?id=" + storeId;
    }

    @RequestMapping(value = "edit/user/{userId}/item/{itemId}/store", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable int itemId,@RequestParam int id, @PathVariable int userId){

        model.addAttribute("storeId",id);

        //grabs previous item from database
        Item item = itemDao.findOne(itemId);
        Store store = storeDao.findOne(id);
        Item newItem = ItemData.getItem(itemId, store);

        model.addAttribute("item",newItem);
        model.addAttribute("title","Edit " + newItem.getName());

        return "inventory/edit";
    }

    @RequestMapping(value ="edit", method = RequestMethod.POST)
    public String processEdit(Model model, @RequestParam int storeId, @RequestParam int itemId,
                              @ModelAttribute Item newItem, @RequestParam int userId){

        //edit's the previous item in the database
        Item item = itemDao.findOne(itemId);
        item.setName(newItem.getName());
        item.setBarCode(newItem.getBarCode());
        item.setTotalQty(newItem.getTotalQty());
        item.setAvailable(newItem.getAvailable());
        item.setLocation(newItem.getLocation());
        item.setPrice(newItem.getPrice());

        itemDao.save(item);

        return "redirect:user/" + userId + "?id=" + storeId;
    }
}
