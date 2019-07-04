package com.example.myInventory.controllers;

import com.example.myInventory.models.Store;
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

    ArrayList<Store>stores = new ArrayList<>();

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("title","Username Stores");
        model.addAttribute("stores",stores);
        return "store/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addStoreForm(Model model){

        model.addAttribute("title","Add a Store");
        model.addAttribute(new Store());
        return "store/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddStoreForm(@ModelAttribute @Valid Store store, Errors errors, Model model){

        if(errors.hasErrors()){
            model.addAttribute("title","Add a Store");
            return "store/add";
        }

        stores.add(store);

        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String removeStore(Model model){

        model.addAttribute("title","Remove Stores");
        model.addAttribute("stores",stores);
        return "store/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public  String processRemoveStore(Model model, @RequestParam int [] storeId){
        stores.remove(storeId);
        return "redirect:";
    }
}
