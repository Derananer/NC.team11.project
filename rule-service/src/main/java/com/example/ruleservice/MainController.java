package com.example.ruleservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public Group[] getGroups(){
        List<Group> groups = groupRepository.findAll();
        System.out.println(groupRepository.findAll());
        return groups.toArray(new Group[groups.size()]);
    }

    @RequestMapping(value = "/create-group", method = RequestMethod.POST)
    public Group createGroup(
            @RequestBody Group newGroup
    ){
        System.out.println(newGroup.toString());
        return groupRepository.save(newGroup);
    }

    @RequestMapping(value = "/add-groupelem", method = RequestMethod.POST)
    public GroupElement addGroupElem(
            @RequestBody GroupElement groupElement

    ) throws Exception {
        System.out.println(groupElement.toString());
        if(groupElementRepository.findByGroupIdAndEmployeeId(groupElement.getGroupId(), groupElement.getEmployeeId()) == null)
            return groupElementRepository.save(groupElement);
        else throw new Exception("already exist");
    }
}

