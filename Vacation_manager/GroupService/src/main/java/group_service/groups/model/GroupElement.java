package group_service.groups.model;

import org.springframework.data.annotation.Id;

public class GroupElement {

    @Id
    private String id;

    private String groupId;

    private String employeeId;

    public GroupElement(){}

    public GroupElement(String groupId, String employeeId){
        this.groupId = groupId;
        this.employeeId = employeeId;
    }

    public String getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setId(String id) {
        this.id = id;
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
                "id='" + id + '\'' +
                ", groupId='" + groupId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                '}';
    }
}
