package com.example.ruleservice;


import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface GroupElementRepository extends MongoRepository<GroupElement, String> {

    List<GroupElement> findByEmployeeId(String employeeId);
    List<GroupElement> findByGroupId(String groupId);
}
