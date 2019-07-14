package employee_service.employees.manager;

import employee_service.departments.model.DepartmentRepository;
import employee_service.employees.model.DBentity.Employee;
import employee_service.employees.model.DBentity.EmployeeRepository;
import employee_service.employees.model.RESTentity.RestEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeManager {
    public EmployeeManager() {
    }

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    RestTemplate restTemplate;

    public RestEmployee[] getEmployees(
            String departmentId
    ) {
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        List<RestEmployee> restEmployees = new ArrayList<>(employees.size());
        for (Employee employee: employees
             ) {
            restEmployees.add(new RestEmployee(employee));
        }
        return restEmployees.toArray(new RestEmployee[0]);
    }


    public Employee[] getEmployeeByGroup(
            String departmentId,
            String groupId
    ) {
        String[] employeesIds = restTemplate.getForObject("http://localhost:8082/emp-ids-by-group?groupId={groupId}", String[].class, groupId);
        ArrayList<Employee> employees = new ArrayList<>();
        for (String empId :
                employeesIds
        ) {
            employees.add(employeeRepository.findByIdAndDepartmentId(empId, departmentId));
        }
        return employees.toArray(new Employee[0]);
    }


    public Employee getEmployee(
            String departmentId,
            String id
    ) {
        return employeeRepository.findByIdAndDepartmentId(id, departmentId);
    }


    public Employee addEmployee(//need review
            String departmentId,
            Employee employee
    ) throws Exception {
        employee.setDepartmentId(departmentId);
        employee = employeeRepository.save(employee);

        return employee;
    }


    public Employee updateEmployee(
            String departmentId,
            Employee employee
    ) {
        employee = employeeRepository.save(employee);
        System.out.println(employee);
        return employee;
    }


    public void deleteEmployee(
            String departmentId,
            Employee employee
    ) throws Exception {
        employeeRepository.delete(employee);
        restTemplate.getForObject("http://localhost:8082/remove-employee?departmentId={departmentId}&employeeId={employeeId}", Void.class, departmentId, employee.getId());
    }
}
