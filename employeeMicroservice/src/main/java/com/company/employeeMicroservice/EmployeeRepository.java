package com.company.employeeMicroservice;


import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface EmployeeRepository extends MongoRepository<Employee, String> {

    List<Employee> findByFirstName(String firstName);
    List<Employee> findByOrganisationId(String organisationId);

}
