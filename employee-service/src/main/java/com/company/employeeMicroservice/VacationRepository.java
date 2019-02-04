package com.company.employeeMicroservice;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VacationRepository extends MongoRepository<Vacation, String> {

    Vacation findByEmployeeId(String employeeId);
}