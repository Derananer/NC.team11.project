package com.company.employeeMicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @RequestMapping(value = "/create-new-department", method = RequestMethod.GET)
    public String createDepartment(
            @RequestParam(value = "name") String departmentName
            //@RequestParam(value = "info") String someInfo
    ) throws Exception {
        Department dep = departmentRepository.save(new Department(departmentName, null));
        if( dep == null)
            throw new Exception("didn`t save");
        else return dep.getId();



    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public Employee[] getEmployees(
            @RequestHeader("department") String departmentId
    ){
        List<Employee> emps = employeeRepository.findByDepartmentId(departmentId);
        return emps.toArray(new Employee[emps.size()]);
    }

    @RequestMapping(value = "/employees-by-group", method = RequestMethod.GET)
    public Employee[] getEmployeeByGroup(
            @RequestParam String groupId,
            @RequestHeader("token") String token,
            @RequestHeader("department") String departmentId
    ){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", token);
        System.out.println("token from eployees-by=groups : " + token);
        String[] empsId = restTemplate.getForObject(
                //"http://localhost:8079/services/rule-service/emp-ids-by-group",
                "http://localhost:8082/emp-ids-by-group?groupId={groupId}", String[].class, groupId);
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
            @RequestParam String id,
            @RequestHeader("department") String departmentId
    ){
        return employeeRepository.findById(id).get();
    }

    @PostMapping("/add-employee")
    public Employee addEmployee(
            @RequestBody Employee emp,
            @RequestHeader("department") String departmentId
    ){
        System.out.println(emp.toString());
        emp = employeeRepository.save(emp);
        return emp;
    }

    @PostMapping("/update-employee")
    public Employee updateEmployee(
            @RequestBody Employee emp,
            @RequestHeader("department") String departmentId
    ){
        System.out.println(emp);
        employeeRepository.deleteById(emp.getId());
        emp.setId(null);
        employeeRepository.save(emp);
        return emp;
    }
}
