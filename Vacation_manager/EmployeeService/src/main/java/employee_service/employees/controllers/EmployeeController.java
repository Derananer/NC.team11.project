package employee_service.employees.controllers;

import employee_service.EmployeeAndVacation;
import employee_service.employees.manager.EmployeeManager;
import employee_service.employees.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeManager employeeManager;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public EmployeeAndVacation[] getEmployees(
            @RequestHeader("department") String departmentId
    ){
        return employeeManager.getEmployees(departmentId);
    }

    @RequestMapping(value = "/employees-by-group", method = RequestMethod.GET)
    public Employee[] getEmployeeByGroup(
            @RequestHeader("department") String departmentId,
            @RequestParam String groupId
    ){
        return employeeManager.getEmployeeByGroup(departmentId, groupId);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Employee getEmployee(
            @RequestHeader("department") String departmentId,
            @RequestParam String id

    ){
        return employeeManager.getEmployee(departmentId, id);
    }

    @PostMapping("/add-employee")
    public EmployeeAndVacation addEmployee(
            @RequestHeader("department") String departmentId,
            @RequestBody Employee employee
    ) throws Exception {
        return employeeManager.addEmployee(departmentId, employee);
    }

    @PostMapping("/update-employee")
    public EmployeeAndVacation updateEmployee(
            @RequestHeader("department") String departmentId,
            @RequestBody EmployeeAndVacation employeeAndVacation
    ){
        return employeeManager.updateEmployee(departmentId, employeeAndVacation);
    }

    @RequestMapping(value = "/delete-employee", method = RequestMethod.POST)
    public Boolean deleteEmployee(
            @RequestHeader(value = "department") String departmentId,
            @RequestBody EmployeeAndVacation employeeAndVacation
    ) throws Exception {
        return deleteEmployee(departmentId, employeeAndVacation);
    }

}
