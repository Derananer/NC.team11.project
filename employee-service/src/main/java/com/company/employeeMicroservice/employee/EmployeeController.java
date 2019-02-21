package com.company.employeeMicroservice.employee;

import com.company.employeeMicroservice.EmployeeAndVacation;
import com.company.employeeMicroservice.department.Department;
import com.company.employeeMicroservice.department.DepartmentRepository;
import com.company.employeeMicroservice.employee.Employee;
import com.company.employeeMicroservice.employee.EmployeeRepository;
import com.company.employeeMicroservice.vacation.Vacation;
import com.company.employeeMicroservice.vacation.VacationController;
import com.company.employeeMicroservice.vacation.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    VacationController vacationController;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    RestTemplate restTemplate;

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
        String[] empsId = restTemplate.getForObject("http://localhost:8082/emp-ids-by-group?groupId={groupId}", String[].class, groupId);
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
        Vacation[] vacations = new Vacation[0];
        //System.out.println(vacationController.addEmptyVacation(emp.getId()));
        EmployeeAndVacation employeeAndVacation = new EmployeeAndVacation(employee, vacations);
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
