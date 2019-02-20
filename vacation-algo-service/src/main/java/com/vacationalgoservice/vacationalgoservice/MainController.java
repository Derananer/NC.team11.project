package com.vacationalgoservice.vacationalgoservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value="/check", method = RequestMethod.POST)
    public boolean check(

    ){
        return true;
    }

    @RequestMapping(value = "/generate",method = RequestMethod.POST)
    public VacationedEmployee[] generate(
            @RequestHeader(value = "rule") String ruleNumber,
            @RequestBody VacationedEmployee[] vacationedEmployees
    ){
        //HttpHeaders headers = new HttpHeaders();
        //headers.set("department", department);

        /*
        Map<String, String> params = new HashMap<String, String>();
        params.put("msisdn", msisdn);
        params.put("email", email);
        params.put("clientVersion", clientVersion);
        params.put("clientType", clientType);
        params.put("issuerName", issuerName);
        params.put("applicationName", applicationName);
*/

       // HttpEntity entity = new HttpEntity(headers);
        //HttpEntity<VacationedEmployee[]> response = restTemplate.exchange("http://localhost:8081/vacation-employees", HttpMethod.GET, entity, VacationedEmployee[].class);
        Algorithms algorithms = new Algorithms(vacationedEmployees, 2017);
        algorithms.setRuleNumber(Integer.parseInt(ruleNumber));
        try {
            algorithms.setVacations();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (VacationedEmployee emp:
                vacationedEmployees
        ) {
            System.out.println(emp.toString());
        }
        return algorithms.getVacationedEmployees();
        //System.out.println(response.getBody().toString());
    }




    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
