package employee_service;

import employee_service.employees.model.Employee;
import employee_service.vacations.model.Vacation;

import java.util.Arrays;

public class EmployeeAndVacation {

    public Employee employee;
    public Vacation[] vacations;

    public EmployeeAndVacation(Employee employee, Vacation[] vacations) {
        this.employee = employee;
        this.vacations = vacations;
    }

    public EmployeeAndVacation() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Vacation[] getVacations() {
        return vacations;
    }

    public void setVacations(Vacation[] vacations) {
        this.vacations = vacations;
    }

    @Override
    public String toString() {
        return "EmployeeAndVacation{" +
                "employees=" + employee.toString() +
                ", vacations=" + Arrays.toString(vacations) +
                '}';
    }
}
