package com.company.employeeMicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @RequestMapping("/getEmployees")
    public Employee[] getEmployee(){
        ArrayList<Employee> emps= employeeRepository.findByOrganisationId(organisationRepository.findByOrganisationName("ООО СКС").getId());
        return emps.toArray(new Employee[emps.size()]);
    }
    @PostMapping("/addEmployee")
    public void addEmployee(
            //@RequestParam(value="firstName")String firstName,
            //@RequestParam(value="lastName")String lastName,
            //@RequestParam(value="secondName")String secondName
            @RequestParam Employee emp
    ){

       //Employee emp = new Employee(firstName, lastName, secondName, null);
        System.out.println("get " + emp.toString());
    }
}
