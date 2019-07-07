package employee_service.employees.manager;

import employee_service.EmployeeAndVacation;
import employee_service.GroupElement;
import employee_service.departments.model.Department;
import employee_service.departments.model.DepartmentRepository;
import employee_service.employees.model.Employee;
import employee_service.employees.model.EmployeeRepository;
import employee_service.vacations.controllers.VacationController;
import employee_service.vacations.model.Vacation;
import employee_service.vacations.model.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeManager {
    public EmployeeManager() {
    }

    @Autowired
    VacationController vacationController;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    RestTemplate restTemplate;

    public EmployeeAndVacation[] getEmployees(
            String departmentId
    ) {
        List<Employee> emps = employeeRepository.findByDepartmentId(departmentId);
        List<EmployeeAndVacation> employeeAndVacationList = new ArrayList<>();
        for (Employee emp :
                emps
        ) {
            employeeAndVacationList.add(new EmployeeAndVacation(emp, vacationRepository.findByEmployeeId(emp.getId()).toArray(new Vacation[0])));
        }
        return employeeAndVacationList.toArray(new EmployeeAndVacation[0]);
    }


    public Employee[] getEmployeeByGroup(
            String departmentId,
            String groupId
    ) {
        String[] empsId = restTemplate.getForObject("http://localhost:8082/emp-ids-by-group?groupId={groupId}", String[].class, groupId);
        ArrayList<Employee> employees = new ArrayList<>();
        for (String empId :
                empsId
        ) {
            employees.add(employeeRepository.findByIdAndDepartmentId(empId, departmentId));
        }
        return employees.toArray(new Employee[employees.size()]);
    }


    public Employee getEmployee(
            String departmentId,
            String id

    ) {
        return employeeRepository.findByIdAndDepartmentId(id, departmentId);
    }


    public EmployeeAndVacation addEmployee(
            String departmentId,
            Employee employee
    ) throws Exception {
        System.out.println(employee.toString());
        employee.setDepartmentId(departmentId);
        employee = employeeRepository.save(employee);
        Vacation[] vacations = new Vacation[0];
        //System.out.println(vacationController.addEmptyVacation(emp.getId()));
        EmployeeAndVacation employeeAndVacation = new EmployeeAndVacation(employee, vacations);
        HttpHeaders headers = new HttpHeaders();
        headers.set("departments", departmentId);
        Optional<Department> department = departmentRepository.findById(departmentId);
        try {
            if (department.isPresent()) {
                GroupElement groupElement = new GroupElement(department.get().getStandardGroupId(), employee.getId());
                HttpEntity entity = new HttpEntity(groupElement, headers);
                HttpEntity<Boolean> response = restTemplate.exchange("http://localhost:8082/add-group-elem", HttpMethod.GET, entity, Boolean.class, employee.getId());
            } else throw new Exception("No such exception");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return employeeAndVacation;
    }


    public EmployeeAndVacation updateEmployee(
            String departmentId,
            EmployeeAndVacation employeeAndVacation
    ) {
        System.out.println(employeeAndVacation);
        employeeRepository.deleteById(employeeAndVacation.employee.getId());
        employeeAndVacation.employee = employeeRepository.save(employeeAndVacation.employee);
        System.out.println(employeeAndVacation);
        /*for (int i = 0; i < employeeAndVacation.vacations.length; i++) {
            employeeAndVacation.vacations[i].setEmployeeId(employeeAndVacation.employees.getId());
            employeeAndVacation.vacations[i] = vacationRepository.save(employeeAndVacation.vacations[i]);
        }*/
        return employeeAndVacation;
    }


    public Boolean deleteEmployee(
            String departmentId,
            EmployeeAndVacation employeeAndVacation
    ) throws Exception {
        Employee employee = null;
        if ((employee = employeeRepository.findByIdAndDepartmentId(employeeAndVacation.employee.getId(), departmentId)) == null)
            try {
                throw new Exception("No such employees id=" + employeeAndVacation.employee.getId());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        employeeRepository.deleteById(employee.getId());
        vacationRepository.deleteByEmployeeId(employee.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.set("departments", departmentId);
        HttpEntity entity = new HttpEntity(headers);
        HttpEntity<Boolean> response = restTemplate.exchange("http://localhost:8082/delete-group-elem", HttpMethod.GET, entity, Boolean.class, employee.getId());
        return response.getBody();
    }
}
