package com.company.employeeMicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public Employee[] getEmployee(){
        List<Employee> emps = employeeRepository.findAll();
        return emps.toArray(new Employee[emps.size()]);
    }

    @RequestMapping(value = "/employees-by-group", method = RequestMethod.GET)
    public Employee[] getEmployee(
            @RequestBody int groupNumber
    ){
        RestTemplate restTemplate = new RestTemplate();
        String[] empsId = restTemplate.getForObject("http://localhost:8080/services/rule-service/emp-ids-by-group", String[].class, groupNumber);
        ArrayList<Employee> employees = new ArrayList<>();
        for (String empId:
                empsId
             ) {
            employees.add(employeeRepository.findById(empId).get());
        }
        return employees.toArray(new Employee[employees.size()]);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Employee getEmployee(
            @RequestParam String id
    ){
        return employeeRepository.findById(id).get();
    }

    @PostMapping("/add-employee")
    public Employee addEmployee(
            @RequestBody Employee emp
    ){
        System.out.println(emp.toString());
        emp = employeeRepository.save(emp);
        return emp;
    }

    @PostMapping("/update-employee")
    public Employee updateEmployee(
            @RequestBody Employee emp
    ){
        System.out.println(emp);
        employeeRepository.deleteById(emp.getId());
        emp.setId(null);
        employeeRepository.save(emp);
        return emp;
    }
}
