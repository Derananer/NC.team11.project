package employee_service.department;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Department {
    @Id
    private String id;
    private String departmentName;
    private String standardGroupId;
    private String someInfo;

    public String getStandardGroupId() {
        return standardGroupId;
    }

    public void setStandardGroupId(String standardGroupId) {
        this.standardGroupId = standardGroupId;
    }

    public Department(String departmentName, String someInfo) {
        this.departmentName = departmentName;
        this.someInfo = someInfo;
    }

    public String getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getSomeInfo() {
        return someInfo;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setSomeInfo(String someInfo) {
        this.someInfo = someInfo;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", standardGroupId='" + standardGroupId + '\'' +
                ", someInfo='" + someInfo + '\'' +
                '}';
    }
}
