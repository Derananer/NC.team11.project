package employee_service.employees.controllers;

import employee_service.employees.manager.EmployeeManager;
import employee_service.employees.model.DBentity.Employee;
import employee_service.employees.model.RESTentity.RestEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeManager employeeManager;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public RestEmployee[] getEmployees(
            @RequestHeader("department") String departmentId
    ){
        return employeeManager.getEmployees(departmentId);
    }

    @RequestMapping(value = "/employees-by-group", method = RequestMethod.GET)//maybe delete
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
    public Employee addEmployee(
            @RequestHeader("department") String departmentId,
            @RequestBody Employee employee
    ) throws Exception {
        return employeeManager.addEmployee(departmentId, employee);
    }

    @PostMapping("/update-employee")
    public Employee updateEmployee(
            @RequestHeader("department") String departmentId,
            @RequestBody Employee employee
    ){
        return employeeManager.updateEmployee(departmentId, employee);
    }

    @RequestMapping(value = "/remove-employee", method = RequestMethod.POST)
    public void deleteEmployee(
            @RequestHeader(value = "department") String departmentId,
            @RequestBody Employee employee
    ) throws Exception {
        employeeManager.deleteEmployee(departmentId, employee);
    }

}
