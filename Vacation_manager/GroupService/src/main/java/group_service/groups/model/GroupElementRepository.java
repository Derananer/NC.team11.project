package group_service.groups.model;


import group_service.groups.model.GroupElement;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface GroupElementRepository extends MongoRepository<GroupElement, String> {

    GroupElement findByGroupIdAndEmployeeId(String groupId, String employeeId);
    GroupElement deleteByEmployeeId(String employeeId);
    List<GroupElement> findByGroupId(String groupId);
    List<GroupElement> deleteByGroupId(String groupId);
}
