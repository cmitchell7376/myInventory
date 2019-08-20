package com.example.myInventory.controllers;

import com.example.myInventory.models.User;
import com.example.myInventory.models.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model){

        model.addAttribute("title","Please Login");

        return "home/login";
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String registration(Model model){

        model.addAttribute(new User());
        model.addAttribute("title","Registration");

        return "home/registration";
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String createNewUser(Model model, @ModelAttribute @Valid User user, Errors errors, BindingResult bindingResult){

        User userExists = userService.findUserByEmail(user.getEmail());

        if(userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if(bindingResult.hasErrors()) {
            model.addAttribute("title", "Registration");
            return "home/registration";
        }

        userService.saveUser(user);
        model.addAttribute("successMessage", "User has been registered successfully");
        return "home/registration";
    }

    @RequestMapping(value = "")
    public String index(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("userId",user.getId());
        model.addAttribute("title","Welcome " + user.getUsername());
        model.addAttribute("adminMessage","Content Available Only for Users with Admin Role");

        return "home/index";
    }
}
