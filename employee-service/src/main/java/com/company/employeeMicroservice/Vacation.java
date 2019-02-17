package com.company.employeeMicroservice;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Vacation {

    @Id
    private String id;

    private String employeeId;
    private int day;
    private int mounth;
    private int year;
    private int numberOfDays;


    public Vacation() {
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

    public Vacation(String id, String employeeId, int day, int mounth, int year, int numberOfDays) {
        this.id = id;
        this.employeeId = employeeId;
        this.day = day;
        this.mounth = mounth;
        this.year = year;
        this.numberOfDays = numberOfDays;
    }
}
