package group_service.groups.manager;

import group_service.RuledGroup;
import group_service.groups.model.Group;
import group_service.groups.model.GroupElement;
import group_service.groups.model.GroupElementRepository;
import group_service.groups.model.GroupRepository;
import group_service.rules.model.Rule;
import group_service.rules.model.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class GroupManager {
    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    GroupElementRepository groupElementRepository;

    @Autowired
    GroupRepository groupRepository;


    public RuledGroup[] getGroups(
            String departmentId
    ) {
        List<Group> groups = groupRepository.findByDepartmentId(departmentId);
        System.out.println("groups " + groupRepository.findAll());
        ArrayList<RuledGroup> ruledGroups = new ArrayList<>();
        for (Group group :
                groups
        ) {
            Optional<Rule> rule = ruleRepository.findById(group.getRuleId());
            if (rule.isPresent()) {
                ruledGroups.add(new RuledGroup(group.getId(), rule.get().getRuleName(), rule.get().getDescription(), rule.get().getRuleNumber()));
                System.out.println("group_service/rules " + rule.get().toString());
                System.out.println("ruledGroup " + ruledGroups.get(ruledGroups.size() - 1));
            }
        }
        System.out.println("ruledGroups " + ruledGroups);
        return ruledGroups.toArray(new RuledGroup[ruledGroups.size()]);
    }


    public String createStandardGroup(
            String departmentId
    ) {
        Rule rule = ruleRepository.findByRuleNumber(3);
        Group group = groupRepository.save(new Group(departmentId, rule.getId()));
        return group.getId();
    }


    public RuledGroup createGroup(
            String departmentId,
            String ruleId
    ) throws Exception {
        Group newGroup = new Group(departmentId, ruleId);
        //newGroup.setDepartmentId(departmentId);
        System.out.println("new Group" + newGroup.toString());
        newGroup = groupRepository.save(newGroup);
        Optional<Rule> rule = ruleRepository.findById(newGroup.getRuleId());
        if (rule.isPresent())
            return new RuledGroup(groupRepository.save(newGroup).getId(), rule.get().getRuleName(), rule.get().getDescription(), rule.get().getRuleNumber());
        else throw new Exception("no such group_service.rules");
    }


    public GroupElement addGroupElem(
            String departmentId,
            GroupElement groupElement
    ) throws Exception {
        GroupElement GE = null;
        try {
            System.out.println(groupElement.toString());
            if ((GE = groupElementRepository.findByGroupIdAndEmployeeId(groupElement.getGroupId(), groupElement.getEmployeeId())) == null) {
                GE = groupElementRepository.save(groupElement);
                System.out.println("Group element added: " + GE.toString());
                return GE;
            } else throw new Exception("Group element already exist ");
        } catch (Exception e) {
            System.out.println(e.getMessage() + ": " + GE.toString());
            throw e;
        }
    }


    public Boolean deleteGroupElement(
            String departmentId,
            String employeeId
    ) {
        groupElementRepository.deleteByEmployeeId(employeeId);
        List<GroupElement> groupElements = groupElementRepository.findByGroupId(employeeId);
        System.out.println("delete group_service.groups elem: is last: " + groupElements.isEmpty());
        return true;
    }


    public Boolean deleteGroupElement(
            String departmentId,
            GroupElement groupElement
    ) {
        groupElement = groupElementRepository.deleteByEmployeeId(groupElement.getEmployeeId());
        List<GroupElement> groupElements = groupElementRepository.findByGroupId(groupElement.getGroupId());
        System.out.println("delete group_service.groups elem: is last: " + groupElements.isEmpty());
        return true;
    }


    public String[] getEmployeesByGroup(
            String groupId
    ) {
        List<GroupElement> groups = groupElementRepository.findByGroupId(groupId);
        ArrayList<String> employeeIds = new ArrayList<>();
        for (GroupElement i :
                groups
        ) {
            employeeIds.add(i.getEmployeeId());
        }
        return employeeIds.toArray(new String[employeeIds.size()]);
    }


    public boolean deleteGroup(
            String departmentId,
            String groupId
    ) {
        Group group = groupRepository.deleteByIdAndDepartmentId(groupId, departmentId);
        System.out.println("deleted group_service.groups: " + group.toString());
        List<GroupElement> groupElementList = groupElementRepository.deleteByGroupId(group.getId());
        System.out.println("deleted group_service.groups elements: " + Arrays.toString(groupElementList.toArray(new GroupElement[0])));
        return true;
    }
}
