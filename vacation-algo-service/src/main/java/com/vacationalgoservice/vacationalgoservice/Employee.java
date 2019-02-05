package com.vacationalgoservice.vacationalgoservice;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    private String id;
    private String departmentId;
    private Date vacationDate;
    private int numberOfDays;

    public Employee(String id, String departmentId, Date vacationDate, int numberOfDays) {
        this.id = id;
        this.departmentId = departmentId;
        this.vacationDate = vacationDate;
        this.numberOfDays = numberOfDays;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Date getVacationDate() {
        return vacationDate;
    }

    public void setVacationDate(Date vacationDate) {
        this.vacationDate = vacationDate;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", vacationDate=" + vacationDate +
                ", numberOfDays=" + numberOfDays +
                '}';
    }
}
