package employee_service.department;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface DepartmentRepository extends MongoRepository<Department, String> {

    Department findByDepartmentName(String departmentName);

}
