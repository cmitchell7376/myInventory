package com.example.myInventory.controllers;

import com.example.myInventory.models.Company;
import com.example.myInventory.models.Equipment;
import com.example.myInventory.models.Tool;
import com.example.myInventory.models.data.repository.CompanyDao;
import com.example.myInventory.models.data.repository.ToolDao;
import com.example.myInventory.models.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("tool")
public class ToolController {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToolDao toolDao;

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
        model.addAttribute("equipId", equipId);
        model.addAttribute("username", userRepository.findOne(userId).getUsername());

        return "Tool/index";
    }

    @RequestMapping(value = "/{equipId}/user/{userId}/add/{id}", method = RequestMethod.GET)
    public String addToolForm(Model model,@PathVariable int id, @PathVariable int userId, @PathVariable int equipId){

        model.addAttribute(new Tool());
        model.addAttribute("storeId",id);
        model.addAttribute("userId",userId);
        model.addAttribute("equipId", equipId);
        model.addAttribute("username", userRepository.findOne(userId).getUsername());
        model.addAttribute("title","Add Tool");

        return "tool/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processToolAdd(Model model, @ModelAttribute Tool tool, Errors errors, @RequestParam int storeId,
                                 @RequestParam int userId, @RequestParam int equipId){

        if(errors.hasErrors()){
            model.addAttribute("storeId",storeId);
            model.addAttribute("title","Add Tool");

            return "tool/add";
        }

        Company company = companyDao.findOne(storeId);
        List<Equipment> equipmentList = company.getCompanyInventory().getEquipment();
        for (Equipment equip: equipmentList) {
            if(equip.getId() == equipId){
                equip.getTools().add(tool);
            }
        }
        toolDao.save(tool);

        return "redirect:user/" + userId + "/equipId/" + equipId + "?id=" + storeId;
    }


}
