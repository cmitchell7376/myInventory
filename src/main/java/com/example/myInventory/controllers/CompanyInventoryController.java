package com.example.myInventory.controllers;

import com.example.myInventory.models.Company;
import com.example.myInventory.models.CompanyInventory;
import com.example.myInventory.models.Equipment;
import com.example.myInventory.models.Tool;
import com.example.myInventory.models.data.CompanyInventoryData;
import com.example.myInventory.models.data.EquipmentData;
import com.example.myInventory.models.data.SearchData;
import com.example.myInventory.models.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("equipment")
public class CompanyInventoryController {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyInventoryDao companyInventoryDao;

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private ToolDao toolDao;

    @RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
    public String Index(Model model, @RequestParam int id, @PathVariable int userId){

        Company store = companyDao.findOne(id);
        model.addAttribute("items",store.getCompanyInventory().getEquipment());
        model.addAttribute("store",store);
        model.addAttribute("userId",userId);
        model.addAttribute("username", userRepository.findOne(userId).getUsername());
        model.addAttribute("title",store.getCompanyInventory().getName()+" Inventory");

        return "companyInventory/index";
    }

    @RequestMapping(value = "user/{userId}/add/{id}", method = RequestMethod.GET)
    public String addEquipmentForm(Model model,@PathVariable int id, @PathVariable int userId){

        model.addAttribute(new Equipment());
        model.addAttribute("storeId",id);
        model.addAttribute("userId",userId);
        model.addAttribute("username", userRepository.findOne(userId).getUsername());
        model.addAttribute("title","Add Equipment");

        return "companyInventory/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddEquipmentForm(Model model, @ModelAttribute @Valid Equipment equipment, Errors errors, @RequestParam int storeId,
                                          @RequestParam int userId){

        //checks for errors
        if(errors.hasErrors()){
            model.addAttribute("storeId",storeId);
            model.addAttribute("title","Add Equipment");

            return "companyInventory/add";
        }

        //finds the store and it's inventory then adds new item to inventory
        Company company = companyDao.findOne(storeId);
        int inventoryId = company.getCompanyInventory().getId();
        CompanyInventory inventory = companyInventoryDao.findOne(inventoryId);
        equipmentDao.save(equipment);
        inventory.addEquipment(equipment);
        companyInventoryDao.save(inventory);

        return "redirect:user/" + userId + "?id=" + storeId;
    }

    @RequestMapping(value = "user/{userId}/remove/{id}", method = RequestMethod.GET)
    public String removeItemForm(Model model,@PathVariable int id, @PathVariable int userId){

        model.addAttribute("title","Remove Product");
        model.addAttribute("items",companyDao.findOne(id).getCompanyInventory().getEquipment());
        model.addAttribute("storeId",id);
        model.addAttribute("userId", userId);
        model.addAttribute("username",userRepository.findOne(userId).getUsername());

        return "companyInventory/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public  String processRemoveStore(Model model, @RequestParam(value = "itemIds", required = false) List<Integer> itemIds,
                                      @RequestParam int storeId, @RequestParam int userId) {

        Company store = companyDao.findOne(storeId);

        if(itemIds  == null){
            model.addAttribute("title","Remove Product");
            model.addAttribute("items",companyDao.findOne(storeId).getCompanyInventory().getEquipment());
            model.addAttribute("storeId",storeId);
            model.addAttribute("userId", userId);
            model.addAttribute("username",userRepository.findOne(userId).getUsername());
            model.addAttribute("error","please check one of the boxes");

            return "companyInventory/remove";
        }

        List<Tool> tools = new ArrayList<Tool>();
        int size = 0;
        for (int itemId : itemIds) {
            Equipment equip = CompanyInventoryData.checkByName(store, itemId);
                size = equip.getTools().size();
                for (Tool tool:equip.getTools()) {
                    tools.add(tool);
                    equip.getTools().remove(tool);
                }
            companyDao.findOne(storeId).getCompanyInventory().removeEquipment(equip);
            equipmentDao.delete(equip);
        }

        for (Tool tool:tools) {
            toolDao.delete(tool);
        }

        return "redirect:user/" + userId + "/?id=" + storeId;
    }


    @RequestMapping(value = "search/user/{userId}", method = RequestMethod.POST)
    public String search(Model model, @RequestParam int id, @PathVariable int userId,
                         @RequestParam String searchRequest, @RequestParam String searchType){

        Company store = companyDao.findOne(id);
        List<Equipment>items = new ArrayList<>();

        if(searchType.equalsIgnoreCase("Name")){
            items = SearchData.equipmentSearchName(store,searchRequest);
        }

        model.addAttribute("items",items);
        model.addAttribute("store",store);
        model.addAttribute("userId",userId);
        model.addAttribute("title",store.getCompanyInventory().getName()+" Inventory");

        return "companyInventory/index";
    }

    @RequestMapping(value = "edit/user/{userId}/equip/{itemId}/company", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable int itemId,@RequestParam int id, @PathVariable int userId){

        model.addAttribute("storeId",id);

        //grabs previous item from database
        Equipment equipment = equipmentDao.findOne(itemId);
        Company company = companyDao.findOne(id);
        Equipment newEquipment = EquipmentData.getEquipment(itemId, company);

        model.addAttribute("item",newEquipment);
        model.addAttribute("title","Edit " + newEquipment.getName());

        return "companyInventory/edit";
    }

    @RequestMapping(value ="edit", method = RequestMethod.POST)
    public String processEdit(Model model, @RequestParam int storeId, @RequestParam int itemId,
                              @ModelAttribute Equipment newEquipment, @RequestParam int userId){

        //edit's the previous item in the database
        Equipment item = equipmentDao.findOne(itemId);
        item.setName(newEquipment.getName());

        equipmentDao.save(item);

        return "redirect:user/" + userId + "?id=" + storeId;
    }
}