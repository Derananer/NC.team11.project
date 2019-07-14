package employee_service.employees.model.RESTentity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import employee_service.employees.model.DBentity.Employee;
import employee_service.employees.model.DBentity.Vacation;
import org.springframework.data.annotation.Id;

import java.util.Arrays;

public class RestEmployee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String secondName;
    private Vacation[] vacations;

    public Vacation[] getVacations() {
        return vacations;
    }

    public void setVacations(Vacation[] vacations) {
        this.vacations = vacations;
    }

    public RestEmployee() {
        this.id = null;
        this.firstName = null;
        this.lastName = null;
        this.secondName = null;

    }

    public RestEmployee(String firstName, String lastName, String secondName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
    }

    public RestEmployee(Employee employee){
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.secondName = employee.getSecondName();
        this.lastName = employee.getLastName();
        this.vacations = employee.getVacations();
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

    public String getSecondName() {
        return secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
                ", vacations=" + Arrays.toString(vacations) +
                '}';
    }

}
