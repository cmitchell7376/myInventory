package com.example.myInventory.controllers;

import com.example.myInventory.models.Company;
import com.example.myInventory.models.CompanyInventory;
import com.example.myInventory.models.User;
import com.example.myInventory.models.data.SearchData;
import com.example.myInventory.models.data.repository.CompanyDao;
import com.example.myInventory.models.data.repository.CompanyInventoryDao;
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
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToolDao toolDao;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private CompanyInventoryDao companyInventoryDao;

    @RequestMapping(value = "store")
    public String index(Model model, @RequestParam int user){

        User user1 = userRepository.findOne(user);
        model.addAttribute("userId",user1.getId());
        model.addAttribute("username",user1.getUsername());
        model.addAttribute("title",user1.getUsername() + " Companies");
        model.addAttribute("stores", user1.getCompanies());

        return "company/index";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(Model model, @RequestParam int user, @RequestParam String searchRequest){

        User user1 = userRepository.findOne(user);

        List<Company> stores = SearchData.equipmentStoreSearch(user1,searchRequest);
        model.addAttribute("userId",user1.getId());
        model.addAttribute("title",user1.getUsername() + " Companies");
        model.addAttribute("stores", stores);

        return "company/index";
    }

    @RequestMapping(value = "add/user/{userId}", method = RequestMethod.GET)
    public String addStoreForm(Model model, @PathVariable int userId) {

        model.addAttribute("title", "Add Company");
        model.addAttribute("userId", userId);
        model.addAttribute("username", userRepository.findOne(userId).getUsername());
        model.addAttribute(new Company());

        return "company/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddStoreForm(@ModelAttribute @Valid Company company, Errors errors, Model model,
                                      @RequestParam String inventoryName, @RequestParam int userId){
        User user = userRepository.findOne(userId);
        //checks for errors
        if(errors.hasErrors()){
            model.addAttribute("title","Add Company");
            return "company/add";
        }

        //create store and it's inventory
        CompanyInventory inventory = new CompanyInventory(inventoryName);
        companyInventoryDao.save(inventory);
        company.setInventory(inventory);
        user.getCompanies().add(company);
        companyDao.save(company);

        return "redirect:store/?user=" + user.getId();
    }

    @RequestMapping(value = "user/{userId}/remove", method = RequestMethod.GET)
    public String removeStore(Model model, @PathVariable int userId){

        User user = userRepository.findOne(userId);

        model.addAttribute("userId",userId);
        model.addAttribute("title","Remove company");
        model.addAttribute("username",user.getUsername());
        model.addAttribute("stores",user.getCompanies());

        return "company/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public  String processRemoveStore(Model model, @RequestParam(value = "storeIds", required = false) List<Integer> storeIds,
                                      @RequestParam int userId){

        User user = userRepository.findOne(userId);

        if(storeIds  == null){
            model.addAttribute("userId",userId);
            model.addAttribute("title","Remove company");
            model.addAttribute("stores",user.getCompanies());
            model.addAttribute("username",user.getUsername());
            model.addAttribute("error","please check one of the boxes");

            return "company/remove";
        }

        for (int storeId: storeIds) {
            Company company = companyDao.findOne(storeId);
            user.removeCompany(company);
            companyDao.delete(company);
        }
        return "redirect:/equipment/store/?user=" + userId;
    }
}
