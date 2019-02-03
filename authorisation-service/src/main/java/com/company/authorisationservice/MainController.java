package com.company.authorisationservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Autowired
    UserAppRepository userAppRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @RequestMapping(value = "/sing-up", method = RequestMethod.POST)
    public void singUp(
            @RequestBody UserCreation userCreation
            ) throws Exception {
        if(userAppRepository.findByUsername(userCreation.username) != null)
            throw new Exception("user name already exist");
        if(userAppRepository.findByEmail(userCreation.email) != null)
            throw new Exception("email already exist");
        UserApp userApp = new UserApp(
                userCreation.firstName,
                userCreation.lastName,
                userCreation.secondName,
                new String(" 34"),
                userCreation.email,
                userCreation.username,
                encoder.encode(userCreation.password)
        );
        userApp.setRole(Roles.USER);
        if((userApp = userAppRepository.save(userApp)) != null)
            System.out.println(userApp.toString());
        else throw new Exception("don`t save");

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
