package com.company.employeeMicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    VacationController vacationController;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    RestTemplate restTemplate;


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

    /*@RequestMapping(value = "/vacation-employees", method = RequestMethod.GET)
    public VacationedEmployee[] vacationedEmployees(
            @RequestHeader("department") String departmentId
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.set("department", departmentId);
        HttpEntity entity = new HttpEntity(headers);
        HttpEntity<RuledGroup[]> response = restTemplate.exchange("http://localhost:8082/groups", HttpMethod.GET, entity, RuledGroup[].class);
        ArrayList<VacationedEmployee> vacationedEmployees = new ArrayList<>();
        for (RuledGroup ruledGroup:
                response.getBody()
        ) {
            for(Employee employee :
                    getEmployeeByGroup(ruledGroup.getGroupId(),departmentId)
            ) {
                List<Vacation> vacations = vacationRepository.findByEmployeeId(employee.getId());
                Date[] vacationDates = new Date[vacations.size()];
                int[] numbersOfVacationDays = new int[vacations.size()];
                int i = 0;
                for (Vacation vacation:
                        vacations
                ) {
                    vacationDates[i] = vacation.getVacationDate();
                    numbersOfVacationDays[i++] = vacation.getNumberOfDays();
                }
                vacationedEmployees.add(new VacationedEmployee(
                        employee.getId(),
                        departmentId,
                        vacationDates,
                        numbersOfVacationDays,
                        ruledGroup.getRuleNumber(),
                        ruledGroup.getGroupId()
                        )
                );
            }
        }
        return vacationedEmployees.toArray(new VacationedEmployee[0]);
    }*/

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
            //@RequestHeader("token") String token,
            @RequestHeader("department") String departmentId
    ){
        //HttpHeaders headers = new HttpHeaders();
        //headers.set("token", token);
        //System.out.println("token from eployees-by=groups : " + token);
        String[] empsId = restTemplate.getForObject(
                //"http://localhost:8079/services/rule-service/emp-ids-by-group",
                "http://localhost:8082/emp-ids-by-group?groupId={groupId}", String[].class, groupId);
        ArrayList<Employee> employees = new ArrayList<>();
        for (String empId:
                empsId
             ) {
            employees.add(employeeRepository.findByIdAndDepartmentId(empId, departmentId));
        }
        return employees.toArray(new Employee[employees.size()]);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Employee getEmployee(
            @RequestParam String id,
            @RequestHeader("department") String departmentId
    ){
        return employeeRepository.findByIdAndDepartmentId(id, departmentId);
    }

    @PostMapping("/add-employee")
    public Employee addEmployee(
            @RequestBody Employee emp,
            @RequestHeader("department") String departmentId
    ){
        System.out.println(emp.toString());
        emp.setDepartmentId(departmentId);
        emp = employeeRepository.save(emp);
        System.out.println(vacationController.addEmptyVacation(emp.getId()));
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




    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
