package com.company.employeeMicroservice;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VacationRepository extends MongoRepository<Vacation, String> {

    List<Vacation> findByEmployeeId(String employeeId);
    Vacation deleteByEmployeeId(String employeeId);
}
