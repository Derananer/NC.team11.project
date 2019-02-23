package com.company.UserInteraface;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestDataController {


    @GetMapping("/login")
    public String login(Model model){

        return "loginPage";
    }

}
