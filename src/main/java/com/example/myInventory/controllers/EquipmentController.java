package com.example.myInventory.controllers;

import com.example.myInventory.models.User;
import com.example.myInventory.models.data.repository.EquipmentDao;
import com.example.myInventory.models.data.repository.ToolDao;
import com.example.myInventory.models.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("equipment")
public class EquipmentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToolDao toolDao;

    @Autowired
    private EquipmentDao equipmentDao;

    @RequestMapping(value = "store")
    public String index(Model model, @RequestParam int user){

        User user1 = userRepository.findOne(user);
        model.addAttribute("userId",user1.getId());
        model.addAttribute("username",user1.getUsername());
        model.addAttribute("title",user1.getUsername() + " Companies");
        model.addAttribute("stores", user1.getEquipmentStores());

        return "equipment/index";
    }
}
