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

    @RequestMapping(value = "/generate",method = RequestMethod.POST)
    public VacationedEmployee[] generate(
            @RequestBody VacationedEmployee[] vacationedEmployees
    ){
        HttpHeaders headers = new HttpHeaders();
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

        HttpEntity entity = new HttpEntity(headers);
        HttpEntity<VacationedEmployee[]> response = restTemplate.exchange("http://localhost:8081/vacation-employees", HttpMethod.GET, entity, VacationedEmployee[].class);
        for (VacationedEmployee emp:
                response.getBody()
             ) {
            System.out.println(emp.toString());
        }
        Algorithms algorithms = new Algorithms(response.getBody(), 2017);
        try {
            algorithms.setVacations();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return algorithms.getVacationedEmployees();
        //System.out.println(response.getBody().toString());
    }




    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
