package com.example.ruleservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    GroupElementRepository groupElementRepository;

    @Autowired
    GroupRepository groupRepository;


    @RequestMapping(value = "/rule", method = RequestMethod.GET)
    public Rule getRule(
            @RequestParam String id
    ){
        return ruleRepository.findById(id).get();
    }

    @RequestMapping(value = "/rules", method = RequestMethod.GET)
    public Rule[] getRules(){
        List<Rule> rules = ruleRepository.findAll();
        return rules.toArray(new Rule[rules.size()]);
    }

    @RequestMapping(value = "/emp-ids-by-group", method = RequestMethod.GET)
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

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public RuledGroup[] getGroups(
            @RequestHeader("department") String departmentId
    ){
        List<Group> groups = groupRepository.findByDepartmentId(departmentId);
        System.out.println(groupRepository.findAll());
        ArrayList<RuledGroup> ruledGroups = new ArrayList<>();
        for (Group group:
                groups
             ) {
            Optional<Rule> rule = ruleRepository.findById(group.getRuleId());
            if(rule.isPresent())
                ruledGroups.add(new RuledGroup(group,rule.get().getRuleName()));
        }
        System.out.println(ruledGroups);
        return ruledGroups.toArray(new RuledGroup[ruledGroups.size()]);
    }

    @RequestMapping(value = "/create-group", method = RequestMethod.POST)
    public RuledGroup createGroup(
            @RequestBody String ruleId,
            @RequestHeader("department") String departmentId
    ) throws Exception {
        Group newGroup = new Group(departmentId,ruleId);
        //newGroup.setDepartmentId(departmentId);
        System.out.println("new Group" + newGroup.toString());
        newGroup = groupRepository.save(newGroup);
        Optional<Rule> rule = ruleRepository.findById(newGroup.getRuleId());
        if(rule.isPresent())return new RuledGroup(groupRepository.save(newGroup),rule.get().getRuleName());
        else throw new Exception("no such rule");
    }

    @RequestMapping(value = "/add-groupelem", method = RequestMethod.POST)
    public GroupElement addGroupElem(
            @RequestBody GroupElement groupElement

    ) throws Exception {
        System.out.println(groupElement.toString());
        if(groupElementRepository.findByGroupIdAndEmployeeId(groupElement.getGroupId(), groupElement.getEmployeeId()) == null){
            return groupElementRepository.save(groupElement);
        }
        else throw new Exception("already exist");
    }
}

