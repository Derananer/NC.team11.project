package com.vacationalgoservice.vacationalgoservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/generate-vacation",method = RequestMethod.GET)
    public void generate(
            @RequestHeader("department") String department
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.set("department", department);

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
        HttpEntity<Employee[]> response = restTemplate.exchange("http://localhost:8081/employees", HttpMethod.GET, entity, Employee[].class);
        for (Employee emp:
                response.getBody()
             ) {
            System.out.println(emp.toString());
        }
        //System.out.println(response.getBody().toString());
    }




    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
