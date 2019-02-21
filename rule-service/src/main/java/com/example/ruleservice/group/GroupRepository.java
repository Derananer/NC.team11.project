package com.example.ruleservice.group;

import com.example.ruleservice.group.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface GroupRepository extends MongoRepository<Group, String> {

    List<Group> findByDepartmentId(String departmentId);
    Group deleteByIdAAndDepartmentId(String id, String departmentId);
}
