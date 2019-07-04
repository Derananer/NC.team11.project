package set_vacation_service.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import set_vacation_service.Algorithms;
import set_vacation_service.VacationedEmployee;
import set_vacation_service.manager.MainManager;

@RestController
public class MainController {

    @Autowired
    MainManager mainManager;

    @RequestMapping(value="/check", method = RequestMethod.POST)
    public boolean check(
    ){
        return mainManager.check();
    }

    @RequestMapping(value = "/generate",method = RequestMethod.POST)
    public VacationedEmployee[] generate(
            @RequestHeader(value = "rule") String ruleNumber,
            @RequestBody VacationedEmployee[] vacationedEmployees
    ){
        return mainManager.generate(ruleNumber, vacationedEmployees);
        //System.out.println(response.getBody().toString());
    }

}
