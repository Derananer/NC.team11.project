package com.company.authorisationservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/sing-up", method = RequestMethod.POST)
    public User singUp(
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

    @RequestMapping(value = "/sing-in", method = RequestMethod.POST)
    public UserL singIn(
            @RequestBody UserL userL
    ){
        String token = null;
        System.out.println("userName : " + userL.userName + " password : " + userL.password);
        try {
            token =  userService.logIn(userL.userName, userL.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("token : " + token);
        userL.token = token;
        return userL;
    }
}
