package com.company.UserInteraface;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicLong;


@RestController
public class RestDataController {

    private final AtomicLong counter = new AtomicLong();


    @RequestMapping("/greeting")
    public Greeting greeting(){
        return new Greeting(counter.incrementAndGet(), "fuck u");
    }

    @RequestMapping("/getEmployees")
    public Employee[] getEmployees(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:6969/getEmployees", Employee[].class);

    }
    @RequestMapping("/addEmployee")
    public String addEmployee(
            //@RequestParam(value="firstName")String firstName,
            //@RequestParam(value="lastName")String lastName,
            //@RequestParam(value="secondName")String secondName
    ){
        Employee emp = new Employee("Антон", "Лисецкий", "Анатольевич", null);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("http://localhost:6969/addEmployee",emp,Employee.class);
        //return emp;
    }

}
