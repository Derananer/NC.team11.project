package com.company.employeeMicroservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VacationedEmployee {

    private String employeeId;
    private String departmentId;
    private Date[] vacationDate;
    private int[] numberOfDays;
    private String ruleId;
    private String groupId;

    public VacationedEmployee(String employeeId, String departmentId, Date[] vacationDate, int[] numberOfDays, String ruleId, String groupId) {
        this.employeeId = employeeId;
        this.departmentId = departmentId;
        this.vacationDate = vacationDate;
        this.numberOfDays = numberOfDays;
        this.ruleId = ruleId;
        this.groupId = groupId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public VacationedEmployee() {
    }


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Date[] getVacationDate() {
        return vacationDate;
    }

    public void setVacationDate(Date[] vacationDate) {
        this.vacationDate = vacationDate;
    }

    public int[] getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int[] numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public String toString() {
        return "VacationedEmployee{" +
                "employeeId='" + employeeId + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", vacationDate=" + Arrays.toString(vacationDate) +
                ", numberOfDays=" + Arrays.toString(numberOfDays) +
                '}';
    }
}
