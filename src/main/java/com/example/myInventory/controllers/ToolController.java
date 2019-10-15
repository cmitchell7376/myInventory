package com.example.myInventory.controllers;

import com.example.myInventory.models.Company;
import com.example.myInventory.models.Equipment;
import com.example.myInventory.models.data.repository.CompanyDao;
import com.example.myInventory.models.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("tool")
public class ToolController {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "user/{userId}/equipId/{equipId}", method = RequestMethod.GET)
    public String Index(Model model, @RequestParam int id, @PathVariable int userId, @PathVariable int equipId){

        Company store = companyDao.findOne(id);
        List<Equipment> equipmentList = store.getCompanyInventory().getEquipment();

        for (Equipment equip: equipmentList) {
            if(equip.getId() == equipId){
                model.addAttribute("items",equip.getTools());
                model.addAttribute("title",equip.getName() + "'s");
            }
        }

        model.addAttribute("store",store);
        model.addAttribute("userId",userId);
        model.addAttribute("username", userRepository.findOne(userId).getUsername());

        return "Tool/index";
    }
}
