package com.company.employeeMicroservice;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface DepartmentRepository extends MongoRepository<Department, String> {

    Department findByDepartmentName(String departmentName);

}
