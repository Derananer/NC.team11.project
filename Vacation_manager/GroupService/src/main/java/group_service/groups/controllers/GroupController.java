package group_service.groups.controllers;


import group_service.RuledGroup;
import group_service.groups.manager.GroupManager;
import group_service.groups.model.Group;
import group_service.groups.model.GroupElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping(value = "/groups")
@RestController
public class GroupController {

    @Autowired
    GroupManager groupManager;

    @RequestMapping(value = "/all-groups", method = RequestMethod.GET)
    public RuledGroup[] getGroups(
            @RequestHeader("department") String departmentId
    ) {
        return groupManager.getGroups(departmentId);
    }

    @RequestMapping(value = "/create-standard-group", method = RequestMethod.GET)
    public String createStandardGroup(
            @RequestParam("departments") String departmentId
    ) {
        return groupManager.createStandardGroup(departmentId);
    }

    @RequestMapping(value = "/create-group", method = RequestMethod.GET)
    public RuledGroup createGroup(
            @RequestHeader("department") String departmentId,
            @RequestParam String ruleId
    ) throws Exception {
       return groupManager.createGroup(departmentId, ruleId);
    }

    @RequestMapping(value = "/add-group-elem", method = RequestMethod.POST)
    public GroupElement addGroupElem(
            @RequestHeader("department") String departmentId,
            @RequestBody GroupElement groupElement
    ) throws Exception {
        return groupManager.addGroupElem(departmentId, groupElement);
    }

    @RequestMapping(value = "/delete-group-elem", method = RequestMethod.GET)
    public Boolean deleteGroupElement(
            @RequestHeader(value = "department") String departmentId,
            @RequestParam String employeeId
    ) {
        return groupManager.deleteGroupElement(departmentId, employeeId);
    }

    @RequestMapping(value = "/delete-group-elem", method = RequestMethod.POST)
    public Boolean deleteGroupElement(
            @RequestHeader(value = "department") String departmentId,
            @RequestBody GroupElement groupElement
    ) {
        return groupManager.deleteGroupElement(departmentId, groupElement);
    }

    @RequestMapping(value = "/emp-ids-by-group", method = RequestMethod.GET)
    public String[] getEmployeesByGroup(
            @RequestParam String groupId
    ) {
        return groupManager.getEmployeesByGroup(groupId);
    }

    @RequestMapping(value = "/delete-group", method = RequestMethod.GET)
    public boolean deleteGroup(
            @RequestHeader(value = "department") String departmentId,
            @RequestParam String groupId
    ) {
        return groupManager.deleteGroup(departmentId, groupId);
    }
}
