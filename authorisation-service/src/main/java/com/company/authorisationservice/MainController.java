package com.company.authorisationservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User add(
            @RequestBody UserCreation userCreation
            ){
        User user = null;
        try {
             user = userService.add(userCreation);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return user;
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            @RequestParam(value = "userName") String loginString,
            @RequestParam(value = "password") String password
    ){
        return login(loginString, password);
    }
}
