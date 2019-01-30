package com.company.UserInteraface;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.atomic.AtomicLong;


@Controller
public class RestDataController {

    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/login")
    public String login(Model model){

        return "loginPage";
    }

}
