package com.company.employeeMicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        ArrayList<Employee> emps= employeeRepository.findByOrganisationId(organisationRepository.findByOrganisationName("ООО СКС").getId());
        return emps.toArray(new Employee[emps.size()]);
    }
}
