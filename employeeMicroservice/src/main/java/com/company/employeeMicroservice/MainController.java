package com.company.employeeMicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @RequestMapping("/getEmployees")
    public Employee[] getEmployee(){
        List<Employee> emps= employeeRepository.findAll();
        return emps.toArray(new Employee[emps.size()]);
    }
    @PostMapping("/addEmployee")
    public Employee addEmployee(
            @RequestBody Employee emp

    ){
        System.out.println(emp.toString());
        emp = employeeRepository.save(emp);
        return emp;
    }


}
