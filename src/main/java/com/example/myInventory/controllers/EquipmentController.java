package com.example.myInventory.controllers;

import com.example.myInventory.models.EquipmentStore;
import com.example.myInventory.models.Inventory;
import com.example.myInventory.models.User;
import com.example.myInventory.models.data.SearchData;
import com.example.myInventory.models.data.repository.EquipmentStoreDao;
import com.example.myInventory.models.data.repository.InventoryDao;
import com.example.myInventory.models.data.repository.ToolDao;
import com.example.myInventory.models.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("equipment")
public class EquipmentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToolDao toolDao;

    @Autowired
    private EquipmentStoreDao equipmentStoreDao;

    @Autowired
    private InventoryDao inventoryDao;

    @RequestMapping(value = "store")
    public String index(Model model, @RequestParam int user){

        User user1 = userRepository.findOne(user);
        model.addAttribute("userId",user1.getId());
        model.addAttribute("username",user1.getUsername());
        model.addAttribute("title",user1.getUsername() + " Companies");
        model.addAttribute("stores", user1.getEquipmentStores());

        return "equipment/index";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(Model model, @RequestParam int user, @RequestParam String searchRequest){

        User user1 = userRepository.findOne(user);

        List<EquipmentStore> stores = SearchData.equipmentStoreSearch(user1,searchRequest);
        model.addAttribute("userId",user1.getId());
        model.addAttribute("title",user1.getUsername() + " Companies");
        model.addAttribute("stores", stores);

        return "equipment/index";
    }

    @RequestMapping(value = "add/user/{userId}", method = RequestMethod.GET)
    public String addStoreForm(Model model, @PathVariable int userId) {

        model.addAttribute("title", "Add Company");
        model.addAttribute("userId", userId);
        model.addAttribute("username", userRepository.findOne(userId).getUsername());
        model.addAttribute(new EquipmentStore());

        return "equipment/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddStoreForm(@ModelAttribute @Valid EquipmentStore equipmentStore, Errors errors, Model model,
                                      @RequestParam String inventoryName, @RequestParam int userId){
        User user = userRepository.findOne(userId);
        //checks for errors
        if(errors.hasErrors()){
            model.addAttribute("title","Add Company");
            return "equipment/add";
        }

        //create store and it's inventory
        Inventory inventory = new Inventory(inventoryName);
        inventoryDao.save(inventory);
        equipmentStore.setInventory(inventory);
        user.getEquipmentStores().add(equipmentStore);
        equipmentStoreDao.save(equipmentStore);

        return "redirect:store/?user=" + user.getId();
    }

    @RequestMapping(value = "user/{userId}/remove", method = RequestMethod.GET)
    public String removeStore(Model model, @PathVariable int userId){

        User user = userRepository.findOne(userId);

        model.addAttribute("userId",userId);
        model.addAttribute("title","Remove company");
        model.addAttribute("username",user.getUsername());
        model.addAttribute("stores",user.getEquipmentStores());

        return "equipment/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public  String processRemoveStore(Model model, @RequestParam(value = "storeIds", required = false) List<Integer> storeIds,
                                      @RequestParam int userId){

        User user = userRepository.findOne(userId);

        if(storeIds  == null){
            model.addAttribute("userId",userId);
            model.addAttribute("title","Remove company");
            model.addAttribute("stores",user.getEquipmentStores());
            model.addAttribute("username",user.getUsername());
            model.addAttribute("error","please check one of the boxes");

            return "equipment/remove";
        }

        for (int storeId: storeIds) {
            EquipmentStore equipmentStore = equipmentStoreDao.findOne(storeId);
            user.removeEquipmentStore(equipmentStore);
            equipmentStoreDao.delete(equipmentStore);
        }
        return "redirect:/equipment/store/?user=" + userId;
    }
}
