package com.example.myInventory.controllers;

import com.example.myInventory.models.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("inventory")
public class HomeController {

    @RequestMapping(value = "")
    public String index(Model model){

        Item item = new Item("milk","2345",50,3.99);
        model.addAttribute("item",item);
        return "inventory/index";
    }
}
