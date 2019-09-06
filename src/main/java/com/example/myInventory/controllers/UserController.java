package com.example.myInventory.controllers;

import com.example.myInventory.models.User;
import com.example.myInventory.models.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("account")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
    public String index(Model model, @PathVariable int userId){

        User user = userRepository.findOne(userId);
        model.addAttribute("user",user);
        model.addAttribute("userName",user.getUsername());

        return "account/index";
    }
}
