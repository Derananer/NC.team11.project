package employee_service.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String secondName;
    private String departmentId;

    public Employee(){
        this.id = null;
        this.firstName = null;
        this.lastName = null;
        this.secondName = null;
        this.departmentId = null;
    }

    public Employee(String firstName, String lastName, String secondName, String departmentId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.departmentId = departmentId;
    }
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }
}
