package authorisation_service.controllers;


import authorisation_service.security.Roles;
import authorisation_service.model.ApplicationUser;
import authorisation_service.model.ApplicationUserRepository;
import authorisation_service.model.UserCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @RequestMapping(value = "/sing-up", method = RequestMethod.POST)
    public void singUp(
            @RequestBody UserCreation userCreation
            ) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        if(applicationUserRepository.findByUsername(userCreation.username) != null)
            throw new Exception("username already exist");
        if(applicationUserRepository.findByEmail(userCreation.email) != null)
            throw new Exception("email already exist");
        String departmentId = restTemplate.getForObject("http://localhost:8081/create-new-departments?name={name}",String.class,userCreation.departmentName);
        ApplicationUser applicationUser = new ApplicationUser(
                userCreation.firstName,
                userCreation.lastName,
                userCreation.secondName,
                departmentId,
                userCreation.email,
                userCreation.username,
                encoder.encode(userCreation.password)
        );
        applicationUser.setRole(Roles.USER);
        if((applicationUser = applicationUserRepository.save(applicationUser)) != null)
            System.out.println(applicationUser.toString());
        else throw new Exception("don`t save");

    }

}
