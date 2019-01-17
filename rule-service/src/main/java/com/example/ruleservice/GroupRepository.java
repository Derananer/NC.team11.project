package com.example.ruleservice;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface GroupRepository extends MongoRepository<Group, String> {

    List<Group> findByDepartmentId(String departmentId);
}
