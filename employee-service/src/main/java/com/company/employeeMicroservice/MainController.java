package com.company.employeeMicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
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
    public EmployeeAndVacation[] getEmployees(
            @RequestHeader("department") String departmentId
    ){
        List<Employee> emps = employeeRepository.findByDepartmentId(departmentId);
        List<EmployeeAndVacation> employeeAndVacationList = new ArrayList<>();
        for (Employee emp:
                emps
             ) {
            employeeAndVacationList.add(new EmployeeAndVacation(emp, vacationRepository.findByEmployeeId(emp.getId()).toArray(new Vacation[0])));
        }
        return employeeAndVacationList.toArray(new EmployeeAndVacation[0]);
    }

    @RequestMapping(value = "/employees-by-group", method = RequestMethod.GET)
    public Employee[] getEmployeeByGroup(

            @RequestHeader("department") String departmentId,
            @RequestParam String groupId
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
            @RequestHeader("department") String departmentId,
            @RequestParam String id

    ){
        return employeeRepository.findByIdAndDepartmentId(id, departmentId);
    }

    @PostMapping("/add-employee")
    public EmployeeAndVacation addEmployee(
            @RequestHeader("department") String departmentId,
            @RequestBody Employee employee
    ){
        System.out.println(employee.toString());
        employee.setDepartmentId(departmentId);
        employee = employeeRepository.save(employee);
        //System.out.println(vacationController.addEmptyVacation(emp.getId()));
        EmployeeAndVacation employeeAndVacation = new EmployeeAndVacation(employee, null);
        return employeeAndVacation;
    }

    @PostMapping("/update-employee")
    public EmployeeAndVacation updateEmployee(
            @RequestHeader("department") String departmentId,
            @RequestBody EmployeeAndVacation employeeAndVacation
    ){
        System.out.println(employeeAndVacation);
        employeeRepository.deleteById(employeeAndVacation.employee.getId());
        employeeAndVacation.employee = employeeRepository.save(employeeAndVacation.employee);
        System.out.println(employeeAndVacation);
        /*for (int i = 0; i < employeeAndVacation.vacations.length; i++) {
            employeeAndVacation.vacations[i].setEmployeeId(employeeAndVacation.employee.getId());
            employeeAndVacation.vacations[i] = vacationRepository.save(employeeAndVacation.vacations[i]);
        }*/
        return employeeAndVacation;
    }

    @RequestMapping(value = "/delete-employee", method = RequestMethod.POST)
    public Boolean deleteEmployee(
            @RequestHeader(value = "department") String departmentId,
            @RequestBody EmployeeAndVacation employeeAndVacation
    ) throws Exception {
        Employee employee = null;
        if((employee = employeeRepository.findByIdAndDepartmentId(employeeAndVacation.employee.getId(), departmentId)) == null)
            try {
                throw new Exception("No such employee id=" + employeeAndVacation.employee.getId());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        employeeRepository.deleteById(employee.getId());
        vacationRepository.deleteByEmployeeId(employee.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.set("department", departmentId);
        HttpEntity entity = new HttpEntity(headers);
        HttpEntity<Boolean> response = restTemplate.exchange("http://localhost:8082/delete-groupelem", HttpMethod.GET, entity, Boolean.class, employee.getId());
        return response.getBody();
    }


    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
