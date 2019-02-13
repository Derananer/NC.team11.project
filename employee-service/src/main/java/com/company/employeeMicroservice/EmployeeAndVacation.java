package com.company.employeeMicroservice;

public class EmployeeAndVacation {

    Employee employee;
    Vacation[] vacations;

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

}
