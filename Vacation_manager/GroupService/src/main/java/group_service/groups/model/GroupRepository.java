package group_service.groups.model;


import group_service.groups.model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface GroupRepository extends MongoRepository<Group, String> {

    List<Group> findByDepartmentId(String departmentId);
    Group deleteByIdAndDepartmentId(String id, String departmentId);
}
