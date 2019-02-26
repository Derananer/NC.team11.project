package employee_service;

import org.springframework.data.annotation.Id;

public class GroupElement {

    private String groupId;

    private String employeeId;

    public GroupElement(){}

    public GroupElement(String groupId, String employeeId){
        this.groupId = groupId;
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "GroupElement{" +
                "groupId='" + groupId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                '}';
    }
}
