package com.company.authorisationservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {


    @RequestMapping(value = "/sing-up", method = RequestMethod.POST)
    public void singUp(
            @RequestBody UserCreation userCreation
            ){


    }

    @RequestMapping(value = "/sing-in", method = RequestMethod.POST)
    public UserL singIn(
            @RequestBody UserL userL
    ){
        return userL;
    }

    @RequestMapping(value = "/sing-in", method = RequestMethod.GET)
    public String sing(){
        return "hello";
    }
}
