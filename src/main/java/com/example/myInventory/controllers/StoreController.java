package com.example.myInventory.controllers;

import com.example.myInventory.models.Inventory;
import com.example.myInventory.models.Store;
import com.example.myInventory.models.User;
import com.example.myInventory.models.data.SearchData;
import com.example.myInventory.models.data.repository.InventoryDao;
import com.example.myInventory.models.data.repository.StoreDao;
import com.example.myInventory.models.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("store")
public class StoreController {

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private InventoryDao inventoryDao;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "")
    public String index(Model model, @RequestParam int user){

        User user1 = userRepository.findOne(user);
        model.addAttribute("userId",user1.getId());
        model.addAttribute("title",user1.getUsername() + " Companies");
        model.addAttribute("stores", user1.getStores());

        return "store/index";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(Model model, @RequestParam int user, @RequestParam String searchRequest){

        User user1 = userRepository.findOne(user);

        List<Store> stores = SearchData.storeSearch(user1,searchRequest);
        model.addAttribute("userId",user1.getId());
        model.addAttribute("title",user1.getUsername() + " Companies");
        model.addAttribute("stores", stores);

        return "store/index";
    }

    @RequestMapping(value = "add/user/{userId}", method = RequestMethod.GET)
    public String addStoreForm(Model model, @PathVariable int userId){

        model.addAttribute("title","Add Company");
        model.addAttribute("userId",userId);
        model.addAttribute(new Store());

        return "store/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddStoreForm(@ModelAttribute @Valid Store store, Errors errors, Model model,
                                      @RequestParam String inventoryName, @RequestParam int userId){
        User user = userRepository.findOne(userId);
        //checks for errors
        if(errors.hasErrors()){
            model.addAttribute("title","Add Company");
            return "store/add";
        }

        //create store and it's inventory
        Inventory inventory = new Inventory(inventoryName);
        inventoryDao.save(inventory);
        store.setInventory(inventory);
        user.getStores().add(store);
        storeDao.save(store);

        return "redirect:?user=" + user.getId();
    }

    @RequestMapping(value = "user/{userId}/remove", method = RequestMethod.GET)
    public String removeStore(Model model, @PathVariable int userId){

        User user = userRepository.findOne(userId);

        model.addAttribute("userId",userId);
        model.addAttribute("title","Remove company");
        model.addAttribute("stores",user.getStores());

        return "store/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public  String processRemoveStore(Model model, @RequestParam(value = "storeIds", required = false) List<Integer> storeIds,
                                      @RequestParam int userId){

        User user = userRepository.findOne(userId);

        if(storeIds  == null){
            model.addAttribute("userId",userId);
            model.addAttribute("title","Remove company");
            model.addAttribute("stores",user.getStores());
            model.addAttribute("error","please check one of the boxes");

            return "store/remove";
        }

        for (int storeId: storeIds) {
            Store store = storeDao.findOne(storeId);
            user.removeStore(store);
            storeDao.delete(store);
        }
        return "redirect:/store/?user=" + userId;
    }
}
