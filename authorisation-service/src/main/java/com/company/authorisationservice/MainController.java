package com.company.authorisationservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
        RestTemplate restTemplate = new RestTemplate();
        if(userAppRepository.findByUsername(userCreation.username) != null)
            throw new Exception("username already exist");
        if(userAppRepository.findByEmail(userCreation.email) != null)
            throw new Exception("email already exist");
        String departmentId = restTemplate.getForObject("http://localhost:8081/create-new-department?name={name}",String.class,userCreation.departmentName);

        UserApp userApp = new UserApp(
                userCreation.firstName,
                userCreation.lastName,
                userCreation.secondName,
                departmentId,
                userCreation.email,
                userCreation.username,
                encoder.encode(userCreation.password)
        );
        userApp.setRole(Roles.USER);
        if((userApp = userAppRepository.save(userApp)) != null)
            System.out.println(userApp.toString());
        else throw new Exception("don`t save");

    }

}
