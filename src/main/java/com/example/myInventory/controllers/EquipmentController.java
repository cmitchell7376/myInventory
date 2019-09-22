package com.example.myInventory.controllers;

import com.example.myInventory.models.data.repository.EquipmentDao;
import com.example.myInventory.models.data.repository.ToolDao;
import com.example.myInventory.models.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("equipment")
public class EquipmentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToolDao toolDao;

    @Autowired
    private EquipmentDao equipmentDao;
}
