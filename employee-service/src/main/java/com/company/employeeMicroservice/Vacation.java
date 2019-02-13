package com.company.employeeMicroservice;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Vacation {

    @Id
    private String id;

    private String employeeId;
    private Date vacationDate;
    private int numberOfDays;


    public Vacation() {
    }

    public Vacation(String employeeId, Date vacationDate, int numberOfDays){
        this.employeeId = employeeId;
        this.vacationDate = vacationDate;
        this.numberOfDays = numberOfDays;
    }
    public String getId() {
        return id;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Date getVacationDate() {
        return vacationDate;
    }

    public String toString(){
        return String.format(
                "Vacation[id=%s, employeeId='%s', vacationDate='%s', numberOfDays='%s']",
                id, employeeId, vacationDate.toString(), numberOfDays);
    }
}
