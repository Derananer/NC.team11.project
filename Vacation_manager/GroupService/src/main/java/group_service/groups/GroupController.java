package group_service.groups;

import com.example.ruleservice.*;
import com.example.ruleservice.rule.Rule;
import com.example.ruleservice.rule.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/groups")
@RestController
public class GroupController {

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    GroupElementRepository groupElementRepository;

    @Autowired
    GroupRepository groupRepository;

    @RequestMapping(value = "/all-groups", method = RequestMethod.GET)
    public RuledGroup[] getGroups(
            @RequestHeader("department") String departmentId
    ){
        List<Group> groups = groupRepository.findByDepartmentId(departmentId);
        System.out.println("groups " + groupRepository.findAll());
        ArrayList<RuledGroup> ruledGroups = new ArrayList<>();
        for (Group group:
                groups
        ) {
            Optional<Rule> rule = ruleRepository.findById(group.getRuleId());
            if(rule.isPresent()) {
                ruledGroups.add(new RuledGroup(group.getId(), rule.get().getRuleName(), rule.get().getDescription(),rule.get().getRuleNumber()));
                System.out.println("group_service/rules " + rule.get().toString());
                System.out.println("ruledGroup " + ruledGroups.get(ruledGroups.size() - 1));
            }
        }
        System.out.println("ruledGroups " + ruledGroups);
        return ruledGroups.toArray(new RuledGroup[ruledGroups.size()]);
    }

    @RequestMapping(value = "/create-group_service.groups", method = RequestMethod.POST)
    public RuledGroup createGroup(
            @RequestHeader("department") String departmentId,
            @RequestBody String ruleId

    ) throws Exception {
        Group newGroup = new Group(departmentId,ruleId);
        //newGroup.setDepartmentId(departmentId);
        System.out.println("new Group" + newGroup.toString());
        newGroup = groupRepository.save(newGroup);
        Optional<Rule> rule = ruleRepository.findById(newGroup.getRuleId());
        if(rule.isPresent())return new RuledGroup(groupRepository.save(newGroup).getId(),rule.get().getRuleName(),rule.get().getDescription(),rule.get().getRuleNumber() );
        else throw new Exception("no such group_service.rules");
    }

    @RequestMapping(value = "/add-group_service.groups-elem", method = RequestMethod.POST)
    public GroupElement addGroupElem(
            @RequestHeader("department") String departmentId,
            @RequestBody GroupElement groupElement

    ) throws Exception {
        GroupElement GE = null;
        try {
            System.out.println(groupElement.toString());
            if ((GE = groupElementRepository.findByGroupIdAndEmployeeId(groupElement.getGroupId(), groupElement.getEmployeeId())) == null) {
                GE = groupElementRepository.save(groupElement);
                System.out.println("Group element added: " + GE.toString());
                return GE;
            } else throw new Exception("Group element already exist ");
        }catch (Exception e){
            System.out.println(e.getMessage() + ": " + GE.toString());
            throw e;
        }
    }

    @RequestMapping(value = "/delete-group_service.groups-elem", method = RequestMethod.GET)
    public Boolean deleteGroupElement(
            @RequestHeader(value = "department") String departmentId,
            @RequestParam String employeeId
    ){
        groupElementRepository.deleteByEmployeeId(employeeId);
        List<GroupElement> groupElements = groupElementRepository.findByGroupId(employeeId);
        System.out.println("delete group_service.groups elem: is last: " + groupElements == null);
        return true;
    }

    @RequestMapping(value = "/delete-group_service.groups-elem", method = RequestMethod.POST)
    public Boolean deleteGroupElement(
            @RequestHeader(value = "department") String departmentId,
            @RequestBody GroupElement groupElement
    ){
        groupElement = groupElementRepository.deleteByEmployeeId(groupElement.getEmployeeId());
        List<GroupElement> groupElements = groupElementRepository.findByGroupId(groupElement.getGroupId());
        System.out.println("delete group_service.groups elem: is last: " + groupElements == null);
        return true;
    }

    @RequestMapping(value = "/emp-ids-by-group_service.groups", method = RequestMethod.GET)
    public String[] getEmployeesByGroup(
            @RequestParam String groupId
    ){
        List<GroupElement> groups = groupElementRepository.findByGroupId(groupId);
        ArrayList<String> employeeIds = new ArrayList<>();
        for (GroupElement i:
                groups
        ) {
            employeeIds.add(i.getEmployeeId());
        }
        return employeeIds.toArray(new String[employeeIds.size()]);
    }

    @RequestMapping(value = "/delete-group_service.groups", method = RequestMethod.GET)
    public boolean deleteGroup(
            @RequestHeader(value = "department") String departmentId,
            @RequestParam String groupId
    ){
        Group group = groupRepository.deleteByIdAndDepartmentId(groupId,departmentId);
        System.out.println("deleted group_service.groups: " + group.toString());
        List<GroupElement> groupElementList = groupElementRepository.deleteByGroupId(group.getId());
        System.out.println("deleted group_service.groups elements: " + Arrays.toString(groupElementList.toArray(new GroupElement[0])));
        return true;
    }
}
