package group_service.groups.controllers;


import group_service.groups.manager.GroupManager;
import group_service.groups.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/groups")
@RestController
public class GroupController {

    @Autowired
    GroupManager groupManager;

    @RequestMapping(value = "/all-groups", method = RequestMethod.GET)
    public Group[] getGroups(
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
    public Group createGroup(
            @RequestHeader("department") String departmentId,
            @RequestParam String ruleId
    ) throws Exception {
        return groupManager.createGroup(departmentId, ruleId);
    }

    @RequestMapping(value = "/update-group", method = RequestMethod.POST)
    public Group updateGroup(
            @RequestBody Group group
    ) {
        return groupManager.updateGroup(group);
    }

    @RequestMapping(value = "/remove-employee-from-group", method = RequestMethod.GET)
    public void moveEmployeeToStandardGroup(
            @RequestParam("departmentId") String departmentId,
            @RequestParam("employeeId") String employeeId
    ) {
        groupManager.moveEmployeeToStandardGroup(departmentId, employeeId);
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

    @RequestMapping(value = "/remove-employee", method = RequestMethod.GET)
    public void removeEmployee(
            @RequestParam("departmentId") String departmentId,
            @RequestParam("employeeId") String employeeId
    ){
        groupManager.removeEmployee(departmentId, employeeId);
    }
}
