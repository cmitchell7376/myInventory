package com.example.myInventory.controllers;

import com.example.myInventory.models.User;
import com.example.myInventory.models.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String processAccount(Model model, @RequestParam int userId, @RequestParam String first,
                                 @RequestParam String last, @RequestParam String email, @RequestParam String userN){

        User user = userRepository.findOne(userId);

        if(!user.getFirstName().equalsIgnoreCase(first)){
            user.setFirstName(first);
        }
        else if(!user.getLastName().equalsIgnoreCase(last)){
            user.setLastName(last);
        }
        else if(!user.getEmail().equalsIgnoreCase(email)){
            user.setEmail(email);
        }
        else if(!user.getUsername().equalsIgnoreCase(userN)){
            user.setUsername(userN);
        }

        userRepository.save(user);

        return "redirect:/home";
    }
}
