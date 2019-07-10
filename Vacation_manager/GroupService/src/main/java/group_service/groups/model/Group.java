package group_service.groups.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Document(collection = "groups")
public class Group {

    @Id
    private String id;
    private String departmentId;
    private String ruleId;
    private List<String> employeeIDs;

    public List<String> getEmployeeIDs() {
        return employeeIDs;
    }

    public void setEmployeeIDs(List<String> employeeIDs) {
        this.employeeIDs = employeeIDs;
    }

    public Group(){}

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Group(String departmentId, String ruleId) {
        this.departmentId = departmentId;
        this.ruleId = ruleId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", employeeIDs=" + employeeIDs +
                '}';
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public void setId(String id) {
        this.id = id;
    }
}
