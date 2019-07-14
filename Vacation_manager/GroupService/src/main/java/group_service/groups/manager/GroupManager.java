package group_service.groups.manager;

import group_service.groups.model.Group;
import group_service.groups.model.GroupRepository;
import group_service.rules.model.Rule;
import group_service.rules.model.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class GroupManager {
    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    RestTemplate restTemplate;

    public Group[] getGroups(
            String departmentId
    ) {
        return groupRepository.findByDepartmentId(departmentId).toArray(new Group[0]);
    }

    public String createStandardGroup(
            String departmentId
    ) {
        Group group = new Group();
        group.setDepartmentId(departmentId);
        group = groupRepository.save(group);
        return group.getId();
    }

    public Group getStandardGroup(String departmentId) {
        String standardGroupId = restTemplate.getForObject("http://localhost:8081/get-standard-group-id?departmentId={departmentId}", String.class, departmentId);
        return groupRepository.findById(standardGroupId).get();
    }

    public Group createGroup(
            String departmentId,
            String ruleId
    ) throws Exception {
        return groupRepository.save(new Group(departmentId, ruleId));
    }

    public String[] getEmployeesByGroup(
            String groupId
    ) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent()) return optionalGroup.get().getEmployeeIDs().toArray(new String[0]);
        return null;
    }

    public Group addEmployeeToGroup(Group group, String employeeId){

    }

    public Group updateGroup(Group group) {
        return groupRepository.save(group);
    }

    public boolean deleteGroup(
            String departmentId,
            String groupId
    ) {
        Group group = groupRepository.deleteByIdAndDepartmentId(groupId, departmentId);
        System.out.println("deleted group_service.groups: " + group.toString());
        return true;
    }

    public void moveEmployeeToStandardGroup(String departmentId, String employeeId) {
        List<Group> groups = groupRepository.findByDepartmentId(departmentId);
        Group standardGroup = getStandardGroup(departmentId);
        for (Group group :
                groups
        ) {
            if (standardGroup.getId().equals(group.getId()) && group.getEmployeeIDs().remove(employeeId)) {
                updateGroup(group);
                standardGroup.getEmployeeIDs().add(employeeId);
                updateGroup(standardGroup);
                break;
            }
        }
    }

    public void removeEmployee(String departmentId, String employeeId) {
        List<Group> groups = groupRepository.findByDepartmentId(departmentId);
        for (Group group :
                groups
        ) {
            if (group.getEmployeeIDs().remove(employeeId)) updateGroup(group);
        }
    }

}
