package employee_service.employees.model;


import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface EmployeeRepository extends MongoRepository<Employee, String> {

    List<Employee> findByFirstName(String firstName);
    List<Employee> findByDepartmentId(String departmentId);
    Employee findByIdAndDepartmentId(String id, String departmentId);

}
