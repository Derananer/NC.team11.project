package com.company.employeeMicroservice.department;

import com.company.employeeMicroservice.department.Department;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DepartmentRepository extends MongoRepository<Department, String> {

    Department findByDepartmentName(String departmentName);

}
